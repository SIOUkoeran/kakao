package com.example.kakao.predict;

import ai.djl.Model;
import ai.djl.metric.Metrics;
import ai.djl.ndarray.NDArray;
import ai.djl.ndarray.NDManager;
import ai.djl.ndarray.types.Shape;
import ai.djl.nn.Block;
import ai.djl.nn.ParameterList;
import ai.djl.nn.SequentialBlock;
import ai.djl.nn.core.Linear;
import ai.djl.training.DefaultTrainingConfig;
import ai.djl.training.EasyTrain;
import ai.djl.training.Trainer;
import ai.djl.training.dataset.Batch;
import ai.djl.training.dataset.Dataset;
import ai.djl.training.listener.TrainingListener;
import ai.djl.training.loss.L2Loss;
import ai.djl.training.loss.Loss;
import ai.djl.training.optimizer.Optimizer;
import ai.djl.training.tracker.Tracker;
import ai.djl.translate.TranslateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class ModelTrainer {

    private final Logger log = LoggerFactory.getLogger(ModelTrainer.class);


    public Trainer initializeTrainer(NDManager manager, Model model){

        L2Loss l2Loss = Loss.l2Loss();

        DefaultTrainingConfig config = new DefaultTrainingConfig(l2Loss)
                .optOptimizer(
                        Optimizer.sgd().setLearningRateTracker(Tracker.fixed(0.03f))
                                .build()
                )
                .optDevices(manager.getEngine().getDevices(1))
                .addTrainingListeners(TrainingListener.Defaults.logging());
        Trainer trainer = model.newTrainer(config);
        int batchSize = 10;
        trainer.initialize(new Shape(batchSize, 2));
        return trainer;
    }

    public Model trainModel(Trainer trainer, Dataset dataSet, Model model){
        Metrics metrics = new Metrics();
        trainer.setMetrics(metrics);

        int numEpochs = 3;
        for (int epoch = 1; epoch <= numEpochs; epoch++){
            try {
                for (Batch batch : trainer.iterateDataset(dataSet)){
                    EasyTrain.trainBatch(trainer, batch);
                    trainer.step();
                    batch.close();
                }
                trainer.notifyListeners(trainingListener -> trainingListener.onEpoch(trainer));
            } catch (IOException | TranslateException e ) {
                e.printStackTrace();
            }
        }
        return model;
    }

    public float[] getParams(Model model){
        Block block = model.getBlock();
        ParameterList parameters = block.getParameters();
        NDArray wParam = parameters.valueAt(0).getArray();
        NDArray bParam = parameters.valueAt(1).getArray();
        float wFloat = wParam.getFloat();
        float bFloat = bParam.getFloat();
        return new float[]{bFloat, wFloat};
    }

    public void saveModel(Model model, int numEmpoch){
        Path modelDir = Paths.get("../models/lin-reg");
        try {
            Files.createDirectories(modelDir);
            model.setProperty("Epoch", Integer.toString(numEmpoch));
            model.save(modelDir, "lin-reg");
        } catch (IOException e) {
            e.printStackTrace();
            log.error("fail to save Model");
        }
    }
}
