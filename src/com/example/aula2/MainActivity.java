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

	// tempo para trocar de números na tela
	final int TIME = 3000;
	// range de números multiplicados: de 0 à 50
	final int RANGE = 15;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Chamo o layout main_layout nesta Activity
		setContentView(R.layout.main_layout);

		// A variável rand será utilizada para criar números aleatórios
		rand = new Random();
		number1 = rand.nextInt(RANGE);
		number2 = rand.nextInt(RANGE);

		// o resultado da multiplicação é guardado na variável result
		result = number1 * number2;
		
		tvQuestion = (TextView) findViewById(R.id.main_tv_question);

		// busca os TextViews por id que mostram os dois números multiplicados
		tv1 = (TextView) findViewById(R.id.main_number_1);
		tv2 = (TextView) findViewById(R.id.main_number_2);

		// new String() cria strings com os números para passar como parâmetro
		// para o método setText()
		tv1.setText(new String("" + number1));
		tv2.setText(new String("" + number2));

		// busca o EditText por id
		et_result = (EditText) findViewById(R.id.main_et_answer);

		// busca os botões por id
		bt_conferir = (Button) findViewById(R.id.main_bt_conferir);
		bt_resultado = (Button) findViewById(R.id.main_bt_resultado);

		// seta o botão Conferir quando for clicado
		bt_conferir.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// pega o resultado que o usuário digitou no EditText
				int int_result = Integer.parseInt(et_result.getText()
						.toString());
				// compara com o resultado correto
				if (int_result == result)
					Toast.makeText(getApplicationContext(),
							"Parabéns! Você acertou! :)", Toast.LENGTH_SHORT)
							.show();
				else {
					Toast.makeText(getApplicationContext(),
							"Você errou. Tente novamente... :(",
							Toast.LENGTH_SHORT).show();
					Log.v("calc_pergunta", "Resultado esperado:" + result);
				}

				// pega novos números de forma randômica
				number1 = rand.nextInt(RANGE);
				number2 = rand.nextInt(RANGE);
				result = number1 * number2;

				// o postDelayed irá modificar os números da tela após
				// milissegundos definidos na variável TIME
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

		// seta o botão resultado quando for clicado
		bt_resultado.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Toast.makeText(getApplicationContext(),
						"O resultado é " + result, Toast.LENGTH_SHORT).show();
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
