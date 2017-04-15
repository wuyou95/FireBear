package com.wuqi.dev.sqlite;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.wuqi.dev.sqlite.db.CarEntity;
import com.wuqi.dev.sqlite.db.DatabaseTool;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText inputId;
    private EditText inputName;
    private EditText inputSelected;
    private EditText inputModel;
    private EditText inputUUID;

    private TextView textShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputId = (EditText) findViewById(R.id.et_id);
        inputName = (EditText) findViewById(R.id.et_name);
        inputSelected = (EditText) findViewById(R.id.et_selected);
        inputModel = (EditText) findViewById(R.id.et_model);
        inputUUID = (EditText) findViewById(R.id.et_uuid);

        textShow = (TextView) findViewById(R.id.text_show);

        CarEntity car = new CarEntity(0);
        car.setName("123");
        DatabaseTool.getInstance().addCar(car);

    }

    public void addCar (View view) {
        CarEntity car = getCar();
        DatabaseTool.getInstance().addCar(car);
    }

    public void queryCars (View view) {
        List<CarEntity> cars = DatabaseTool.getInstance().queryCars();
        textShow.setText(cars.toString());
    }

    public void updateCar (View view) {
        CarEntity car = getCar();
        DatabaseTool.getInstance().updateCar(car);
    }

    public void removeCar (View view) {
        int id = getEditInt(inputId);
        DatabaseTool.getInstance().removeCar(id);
    }

    private int getEditInt(EditText et){
        String value = et.getText().toString();
        return Integer.parseInt(value);
    }

    private String getEditStr(TextView tv){
        return tv.getText().toString();
    }

    private CarEntity getCar(){
        int id = getEditInt(inputId);
        String name = getEditStr(inputName);
        int selected = getEditInt(inputSelected);
        int model = getEditInt(inputModel);
        String uuid = getEditStr(inputUUID);
        CarEntity car = new CarEntity(id);
        car.setName(name);
        car.setSelected(selected);
        car.setModel(model);
        car.setUuid(uuid);
        return car;
    }

    public void setSelectedCarsID (View view) {
        int id = getEditInt(inputId);
        DatabaseTool.getInstance().changeSelectedCar(id);
    }

    public void setSelectedCarsObj (View view) {
        CarEntity car = getCar();
        DatabaseTool.getInstance().changeSelectedCar(car);
    }
}
