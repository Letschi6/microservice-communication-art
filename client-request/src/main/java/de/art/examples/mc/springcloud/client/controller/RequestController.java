package de.art.examples.mc.springcloud.client.controller;

import de.art.examples.mc.springcloud.client.Main;
import de.art.examples.mc.springcloud.client.feign.OperandFeignInterface;
import de.art.examples.mc.springcloud.client.feign.OperatorFeignInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

@Controller
public class RequestController {
    private static final Logger log = LoggerFactory.getLogger(Main.class);

    private final OperandFeignInterface operandFeignInterface;
    private final OperatorFeignInterface operatorFeignInterface;

    @Autowired
    public RequestController(OperandFeignInterface operandFeignInterface, OperatorFeignInterface operatorFeignInterface) {
        this.operandFeignInterface = operandFeignInterface;
        this.operatorFeignInterface = operatorFeignInterface;
    }


    @GetMapping("/operation")
    public @ResponseBody
    String getOperation() throws ScriptException {
        String function = operandFeignInterface.getOperand() + " "
                + operatorFeignInterface.getOperation() + " "
                + operandFeignInterface.getOperand();
        log.info("Function: " + function);
        ScriptEngineManager mgr = new ScriptEngineManager();
        ScriptEngine engine = mgr.getEngineByName("JavaScript");
        String result = "" + engine.eval(function);
        log.info("Result: " + result);
        return function + " = " + result;
    }

}

