package org.camunda.bpm.getstarted.pizza;

import java.io.Serializable;

import javax.inject.Named;

import org.camunda.bpm.engine.cdi.annotation.BusinessProcessScoped;

//@Named
//@BusinessProcessScoped
public class PizzaProcessVariables implements Serializable{

	private String PizzaOrderId;

	public String getPizzaOrderId() {
		return PizzaOrderId;
	}

	public void setPizzaOrderId(String pizzaOrderId) {
		PizzaOrderId = pizzaOrderId;
	}
	
}
