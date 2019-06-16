package com.example.aula2;

import java.util.Random;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Typeface;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	TextView tv1, tv2;
	TextView tvQuestion;
	Button bt_conferir, bt_resultado;
	EditText et_result;
	int number1, number2, result;
	Random rand;

	// tempo para trocar de n�meros na tela
	final int TIME = 3000;
	// range de n�meros multiplicados: de 0 � 50
	final int RANGE = 15;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Chamo o layout main_layout nesta Activity
		setContentView(R.layout.main_layout);

		// A vari�vel rand ser� utilizada para criar n�meros aleat�rios
		rand = new Random();
		number1 = rand.nextInt(RANGE);
		number2 = rand.nextInt(RANGE);

		// o resultado da multiplica��o � guardado na vari�vel result
		result = number1 * number2;
		
		tvQuestion = (TextView) findViewById(R.id.main_tv_question);

		// busca os TextViews por id que mostram os dois n�meros multiplicados
		tv1 = (TextView) findViewById(R.id.main_number_1);
		tv2 = (TextView) findViewById(R.id.main_number_2);

		// new String() cria strings com os n�meros para passar como par�metro
		// para o m�todo setText()
		tv1.setText(new String("" + number1));
		tv2.setText(new String("" + number2));

		// busca o EditText por id
		et_result = (EditText) findViewById(R.id.main_et_answer);

		// busca os bot�es por id
		bt_conferir = (Button) findViewById(R.id.main_bt_conferir);
		bt_resultado = (Button) findViewById(R.id.main_bt_resultado);

		// seta o bot�o Conferir quando for clicado
		bt_conferir.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// pega o resultado que o usu�rio digitou no EditText
				int int_result = Integer.parseInt(et_result.getText()
						.toString());
				// compara com o resultado correto
				if (int_result == result)
					Toast.makeText(getApplicationContext(),
							"Parab�ns! Voc� acertou! :)", Toast.LENGTH_SHORT)
							.show();
				else {
					Toast.makeText(getApplicationContext(),
							"Voc� errou. Tente novamente... :(",
							Toast.LENGTH_SHORT).show();
					Log.v("calc_pergunta", "Resultado esperado:" + result);
				}

				// pega novos n�meros de forma rand�mica
				number1 = rand.nextInt(RANGE);
				number2 = rand.nextInt(RANGE);
				result = number1 * number2;

				// o postDelayed ir� modificar os n�meros da tela ap�s
				// milissegundos definidos na vari�vel TIME
				bt_conferir.postDelayed(new Runnable() {

					@Override
					public void run() {
						tv1.setText(new String("" + number1));
						tv2.setText(new String("" + number2));
						et_result.setText(""); // limpa o edittext

					}
				}, TIME);

			}
		});

		// seta o bot�o resultado quando for clicado
		bt_resultado.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Toast.makeText(getApplicationContext(),
						"O resultado � " + result, Toast.LENGTH_SHORT).show();
			}
		});
		
		setLayout();

	}

	void setLayout() {
		Typeface type = Typeface.createFromAsset(getAssets(),
				"Channel.ttf");
		tvQuestion.setTypeface(type);
		bt_conferir.setTypeface(type);
		bt_resultado.setTypeface(type);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
