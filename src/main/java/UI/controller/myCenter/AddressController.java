package UI.controller.myCenter;

import Dao.OrderDao;
import UI.controller.Main;
import UI.controller.ViewHelper;
import entity.Address;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.Callback;

import javax.persistence.Table;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AddressController  extends ViewHelper {

//    @FXML
//    private ComboBox<TableDataModel> tableComBoBox;
    @FXML
    private TableView<Address> addressTable;
    @FXML
    private TableColumn<Address, Boolean> isSelectCol;
    @FXML
    private TableColumn<Address, String> nameCol;
    @FXML
    private TableColumn<Address, String> telCol;
    @FXML
    private TableColumn<Address, String> addressCol;
    @FXML
    private TableColumn<Address, String> remarkCol;
    @FXML
    private ChoiceBox<Table> tableNoChoiceBox;
    @FXML
    private Label messageLabel;

    private ObservableList<Address> addressSource;

    @Override
    public void init() {
        fetchData();
    }

    @FXML
    public void addDealDetailAction(ActionEvent event){
        SingleSelectionModel<Table> select = tableNoChoiceBox.getSelectionModel();
        Table table = select.getSelectedItem();
        if(table==null){
            return;
        }
//        DealManager dealManager = LocalManager.getDealManager();
//        Deal deal = dealManager.getUnCheckOutDealByTable(table);
//        if(deal==null){
//            return;
//        }
//        Set<DealDetail> details = deal.getDetails();
//        //遍历数据源，找到选中的并且数目大于一的food
//        for(int i=0;i<addressSource.size();i++){
//            Address fdm = addressSource.get(i);
//            if(fdm.getSelect()&&fdm.getAmount()>=1){
//                DealDetail dd = new DealDetail();
//                dd.setDeal(deal);
//                dd.setFood(fdm.getFood());
//                boolean hasAdd = false;
//                /*
//                 * 遍历details，检查是否已有dealDetail包含此种food
//                 * 如果有，则直接更改此dealDetail的amount字段，如果没有
//                 * 则添加新的dealDetail
//                 */
//                for(DealDetail dealDetail:details){
//                    Food food = dealDetail.getFood();
//                    if(food.getId()==dd.getFood().getId()){
//                        dealDetail.setAmount(dealDetail.getAmount()+fdm.getAmount());
//                        hasAdd = true;
//                        break;
//                    }
//                }
//                if(!hasAdd){
//                    dd.setAmount(fdm.getAmount());
//                    dd.setPrice(fdm.getFoodPrice());
//                    details.add(dd);
//                }
//
//            }
//        }
//        dealManager.update(deal);
        close(null);

    }

    @FXML
    public void close(ActionEvent event){
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        initTable();
        /*
         * 初识化tableNoChoiceBox中的数据
         */
//        TableManager tableManager = TableManager.getTableManager();
//        List<Table> tables = tableManager.getUsingTable();
//        for(Table table:tables){
//            tableNoChoiceBox.getItems().add(table);;
//        }
//        SingleSelectionModel<Table> selectionModel = tableNoChoiceBox.getSelectionModel();
//        selectionModel.selectFirst();
    }


    public void initTable(){
        addressSource = FXCollections.observableArrayList();
        addressTable.setEditable(true);

        isSelectCol.setCellValueFactory(new PropertyValueFactory<Address,Boolean>("isSelect"));
        nameCol.setCellValueFactory(new PropertyValueFactory<Address,String>("name"));
        telCol.setCellValueFactory(new PropertyValueFactory<Address,String>("tel"));
        addressCol.setCellValueFactory(new PropertyValueFactory<Address,String>("address"));
        remarkCol.setCellValueFactory(new PropertyValueFactory<Address, String>("remark"));



        isSelectCol.setCellFactory(new Callback<TableColumn<Address, Boolean>, TableCell<Address, Boolean>>() {
            @Override
            public TableCell<Address, Boolean> call(TableColumn<Address, Boolean> p) {
                CheckBoxTableCell<Address, Boolean> cell = new CheckBoxTableCell<Address, Boolean>();
                return cell;
            }
        });


        Callback<TableColumn<Address, String>, TableCell<Address, String>> cellEditStringFactory =
                new Callback<TableColumn<Address, String>, TableCell<Address, String>>() {
            @Override
            public TableCell<Address, String> call(TableColumn<Address, String> param) {
                return new EditingCell();
            }
        };

        addressCol.setCellFactory(cellEditStringFactory);


        addressTable.setItems(addressSource);

        //updateObservableListProperties(isSelectCol,amountCol);
    }

    /**
     * 从持久层中获取数据，并加载到表中
     */
    public void fetchData(){
        List<Address> addressArrayList=main.getUser().getAddresses();
        addressSource.addAll(addressArrayList);
        addressTable.setItems(addressSource);
//        new Thread(){
//            @Override
//            public void run() {
//                super.run();
//                Address
//                ArrayList<Address> dao.findMyAllById(main.getUser().getId());
//            }
//        }.start();
//        List<Food> foods = foodManager.getAll();
//        this.foods = foods;
//        loadTableData();
    }


    public static class EditingCell extends TableCell<Address, String> {
        private TextField textField;

        public EditingCell() {
        }

        @Override public void startEdit() {
            super.startEdit();

            if (textField == null) {
                createTextField();
            }
            setText(null);
            setGraphic(textField);
            textField.selectAll();
        }

        @Override public void cancelEdit() {
            super.cancelEdit();
            setText( getItem().toString());
            setGraphic(null);
        }

        @Override public void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);
            if (empty) {
                setText(null);
                setGraphic(null);
            } else {
                if (isEditing()) {
                    if (textField != null) {
                        textField.setText(getString());
                    }
                    setText(null);
                    setGraphic(textField);
                } else {
                    setText(getString());
                    setGraphic(null);
                }
            }
        }

        private void createTextField() {
            textField = new TextField(getString());
            textField.setMinWidth(this.getWidth() - this.getGraphicTextGap() * 2);
            textField.setOnKeyReleased(new EventHandler<KeyEvent>() {
                @Override public void handle(KeyEvent t) {
                    if (t.getCode() == KeyCode.ENTER) {
                        String value = "";
                        try{
                            value = textField.getText();
                        }catch(Exception e){
                            cancelEdit();
                            return;
                        }
                        commitEdit(value);
                    } else if (t.getCode() == KeyCode.ESCAPE) {
                        cancelEdit();
                    }
                }
            });
        }

        private String getString() {
            return getItem() == null ? "" : getItem().toString();
        }
    }


//    /**
//     * 当用户修改此表格中isSelectCol或者amountCol时，更新ObservableLise中的值
//     * @param isSelectCol
//     * @param amountCol
//     */
//    private void updateObservableListProperties(TableColumn<Address,Boolean> isSelectCol, TableColumn<Address,Integer> amountCol){
//
//        amountCol.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Address,Integer>>() {
//
//            @Override
//            public void handle(TableColumn.CellEditEvent<Address, Integer> event) {
//                event.getTableView().getItems().get(event.getTablePosition().getRow()).setAmount(event.getNewValue());
//            }
//        });
//    }

}
