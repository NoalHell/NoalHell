/*
 * ViewHelper.java
 * orderDishesSystem
 * Created by 冯 友超 on 13-7-6.
 * Copyright (c) 2013年 冯 友超. All rights reserved.
 */
package UI.controller;

import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.util.Timer;
import java.util.TimerTask;


/**
 * <code>ViewHelper</code>类实现了一些界面上的方法。
 * @author fengyouchao
 * 
 */
public abstract class ViewHelper {

	/**
	 * 一个由浅变深的动画，用来显示信息。
	 * 
	 * @param message 要显示的信息。
	 * @param messageLabel 显示此条信息的<code>javafx.scene.control.Label</code>对象
	 */
	public void animateMessage(String message,Label messageLabel) {
		FadeTransition ft = new FadeTransition(Duration.millis(1000), messageLabel);
		ft.setFromValue(0.0);
		ft.setToValue(1);
		ft.play();
		messageLabel.setText(message);
	}

	public static void toast(String message,int time) {
		Stage stage=new Stage();
		stage.initStyle(StageStyle.TRANSPARENT);//舞台透明
		TimerTask task= new TimerTask() {
			@Override
			public void run() {
				Platform.runLater(()->stage.close());
			}
		};
		init(stage,message);
		Timer timer=new Timer();
		timer.schedule(task,time);
		stage.show();
	}

	//设置消息
	private static void init(Stage stage,String msg) {
		Label label=new Label(msg);//默认信息
		label.setStyle("-fx-background: rgba(56,56,56,0.7);-fx-border-radius: 25;-fx-background-radius: 25");//label透明,圆角
		label.setLayoutY(500);
		label.setTextFill(Color.rgb(225,255,226));//消息字体颜色
		label.setPrefHeight(50);
		label.setPadding(new Insets(15));
		label.setAlignment(Pos.CENTER);//居中
		label.setFont(new Font(20));//字体大小
		Scene scene=new Scene(label);
		scene.setFill(null);//场景透明
		stage.setScene(scene);
	}
}
