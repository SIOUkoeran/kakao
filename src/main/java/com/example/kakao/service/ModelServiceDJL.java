package com.example.kakao.service;

import ai.djl.Model;
import ai.djl.ndarray.NDManager;
import ai.djl.training.Trainer;
import ai.djl.training.dataset.ArrayDataset;
import com.example.kakao.model.Amount;
import com.example.kakao.model.Bank;
import com.example.kakao.predict.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Month;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ModelServiceDJL implements ModelService {

    private final DBLoader dbLoader;
    private final DataSet dataSet;
    private final ModelTrainer modelTrainer;

    public float predictAmount(Bank bank, Month month){
        int batchSize = 10;
        List<Amount> amounts = dbLoader.loadDB(bank);
        NDManager manager = NDManager.newBaseManager();
        DataPoints dataPoints = DataPoints.syntheticData(manager, amounts);
        ArrayDataset dataSet = this.dataSet.createDataSet(dataPoints.getX(), dataPoints.getY());

        Model model = ModelCreator.createModel();
        Trainer trainer = modelTrainer.initializeTrainer(manager, model);
        Model trainedModel = modelTrainer.trainModel(trainer, dataSet, model);
        float[] params = modelTrainer.getParams(trainedModel);

        String date = "2018" + String.valueOf(month);
        return params[0] * Integer.parseInt(date) + params[1];
    }
}
