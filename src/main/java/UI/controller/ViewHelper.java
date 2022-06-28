
package UI.controller;

import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.Background;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
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

	private Stage dialogStage;
	private ProgressIndicator progressIndicator;

	public void showLoading(Stage primaryStage, String tip) {
		dialogStage = new Stage();
		progressIndicator = new ProgressIndicator();
		// 窗口父子关系
		dialogStage.initOwner(primaryStage);
		dialogStage.initStyle(StageStyle.UNDECORATED);
		dialogStage.initStyle(StageStyle.TRANSPARENT);
		dialogStage.initModality(Modality.APPLICATION_MODAL);
		// progress bar
		Label label = new Label(tip);
		label.setTextFill(Color.BLUE);
		progressIndicator.setProgress(-1F);
		VBox vBox = new VBox();
		vBox.setSpacing(10);
		vBox.setBackground(Background.EMPTY);
		vBox.getChildren().addAll(progressIndicator,label);
		Scene scene = new Scene(vBox);
		scene.setFill(null);
		dialogStage.setScene(scene);
		dialogStage.show();
	}

	public Stage getLoadingStage(){
		return dialogStage;
	}

	public void hideLoading() {
		dialogStage.close();
	}
}
