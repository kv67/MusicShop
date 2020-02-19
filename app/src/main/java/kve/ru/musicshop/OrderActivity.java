package kve.ru.musicshop;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class OrderActivity extends AppCompatActivity {

  private String[] addresses = {"kv67@narod.ru"};
  private String subject;
  private TextView orderTextView;
  private String orderText;

  @SuppressLint("StringFormatInvalid")
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_order);
    setTitle(R.string.your_order);

    subject = getString(R.string.order_subject);
    orderTextView = findViewById(R.id.orderTextView);
    orderText = "";

    Intent intent = getIntent();
    if (intent.hasExtra("customerName")){
      orderText += getString(R.string.customer_name,
          intent.getStringExtra("customerName")) + "\n";
    }
    if (intent.hasExtra("goodName")){
      orderText += getString(R.string.goods_name,
          intent.getStringExtra("goodName")) + "\n";
    }
    if (intent.hasExtra("quantity")){
      orderText += getString(R.string.order_quantity,
          intent.getIntExtra("quantity", 0)) + "\n";
    }
    if (intent.hasExtra("orderPrice")){
      orderText += getString(R.string.order_price_order,
          intent.getDoubleExtra("orderPrice", 0.0),
          getString(R.string.currency)) + "\n";
    }
    orderTextView.setText(orderText);
  }

  public void onClickSendOrder(View view) {
    Intent intent = new Intent(Intent.ACTION_SENDTO);
    intent.setData(Uri.parse("mailto:")); // only email apps should handle this
    intent.putExtra(Intent.EXTRA_EMAIL, addresses);
    intent.putExtra(Intent.EXTRA_SUBJECT, subject);
    intent.putExtra(Intent.EXTRA_TEXT, orderText);
    if (intent.resolveActivity(getPackageManager()) != null) {
      startActivity(intent);
    }
  }
}
