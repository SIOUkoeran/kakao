package com.example.kakao.predict;

import ai.djl.Device;
import ai.djl.ndarray.NDManager;
import ai.djl.nn.SequentialBlock;
import ai.djl.nn.core.Linear;
import ai.djl.repository.zoo.ModelZoo;
import ai.djl.training.DefaultTrainingConfig;
import ai.djl.training.Trainer;
import ai.djl.training.listener.TrainingListener;
import ai.djl.training.loss.L2Loss;
import ai.djl.training.loss.Loss;
import ai.djl.training.optimizer.Optimizer;
import ai.djl.training.tracker.Tracker;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class Model {

    private static ai.djl.Model createModel(){
        ai.djl.Model model = ai.djl.Model.newInstance("lin-reg");
        SequentialBlock net = new SequentialBlock();
        Linear linearBlock = Linear.builder()
                .optBias(true)
                .setUnits(1)
                .build();
        net.add(linearBlock);
        model.setBlock(net);
        return model;
    }
    public Trainer sample(NDManager manager){

        ai.djl.Model model = createModel();
        L2Loss l2Loss = Loss.l2Loss();

        DefaultTrainingConfig config = new DefaultTrainingConfig(l2Loss)
                .optOptimizer(
                        Optimizer.sgd().setLearningRateTracker(Tracker.fixed(0.03f))
                                .build()
                )
                .optDevices(manager.getEngine().getDevices(1))
                .addTrainingListeners(TrainingListener.Defaults.logging());

        return model.newTrainer(config);
    }
}
