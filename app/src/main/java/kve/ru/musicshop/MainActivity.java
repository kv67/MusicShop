package kve.ru.musicshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

  private int quantity = 0;
  private Double price = 0.0;
  private EditText editTextYourName;
  private ImageView imageViewInstrument;
  private TextView textViewPrice;
  private TextView textViewQuantity;
  private Spinner spinnerSelect;
  private ArrayList<String> spinnerItems = new ArrayList<>();
  private Map<String, Double> prices = new HashMap<>();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

    findViews();
    createSpinner();
    createMap();

    spinnerSelect.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
      @Override
      public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        setGoodImage(position);
        setPrice();
      }

      @Override
      public void onNothingSelected(AdapterView<?> parent) {
        // not used yet
      }
    });
  }

  private void findViews(){
    editTextYourName = findViewById(R.id.editTextYourName);
    imageViewInstrument = findViewById(R.id.imageViewInstrument);
    textViewPrice = findViewById(R.id.textViewPrice);
    textViewQuantity = findViewById(R.id.textViewQuantity);
  }

  private void createMap(){
    prices.put(getString(R.string.guitar), 500.0);
    prices.put(getString(R.string.drums), 1500.0);
    prices.put(getString(R.string.keyboard), 1000.0);
  }

  private void createSpinner(){
    spinnerSelect = findViewById(R.id.spinnerSelect);
    spinnerItems.add(getString(R.string.guitar));
    spinnerItems.add(getString(R.string.drums));
    spinnerItems.add(getString(R.string.keyboard));
    ArrayAdapter spinnerAdapter =
        new ArrayAdapter(this, R.layout.spinner_item, spinnerItems);
    spinnerSelect.setAdapter(spinnerAdapter);
  }

  private void  setGoodImage(int position){
    switch (position) {
      case 0:
        imageViewInstrument.setImageResource(R.drawable.guitar);
        break;
      case 1:
        imageViewInstrument.setImageResource(R.drawable.drums);
        break;
      case 2:
        imageViewInstrument.setImageResource(R.drawable.keyboard);
        break;
      default:
        break;
    }
  }

  private void setPrice(){
    String selectedItem = spinnerSelect.getSelectedItem().toString();
    if (prices.containsKey(selectedItem)) {
      price = prices.get(selectedItem) * quantity;
      textViewPrice.setText(String.format("%.2f %s", price,
          getString(R.string.currency)));
    }
  }

  private void addQuantity(int value){
    quantity += value;
    if (quantity < 0) {
      quantity = 0;
    }
    textViewQuantity.setText("" + quantity);
    setPrice();
  }

  public void addToCartOnClick(View view) {
    Order order = new Order(editTextYourName.getText().toString(),
        spinnerSelect.getSelectedItem().toString(), quantity, price);

    Intent intent = new Intent(this, OrderActivity.class);
    intent.putExtra("customerName", order.getClientName());
    intent.putExtra("goodName", order.getGood());
    intent.putExtra("quantity", order.getQuantity());
    intent.putExtra("orderPrice", order.getOrderPrice());
    startActivity(intent);
  }

  public void onClickPlus(View view) {
    addQuantity(1);
  }

  public void onClickMinus(View view) {
    addQuantity(-1);
  }
}
