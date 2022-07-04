package com.example.kakao.predict;

import ai.djl.nn.SequentialBlock;
import ai.djl.nn.core.Linear;
import org.springframework.stereotype.Component;

@Component
public class ModelCreator {
    public static ai.djl.Model createModel(){
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
}
