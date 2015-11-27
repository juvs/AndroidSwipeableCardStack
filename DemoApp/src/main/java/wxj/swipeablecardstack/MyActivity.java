package wxj.swipeablecardstack;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.cardstack.CardStack;


public class MyActivity extends Activity {
    private CardStack mCardStack;
    private CardsDataAdapter mCardAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        mCardStack = (CardStack)findViewById(R.id.container);

        //
        mCardStack.setContentResource(R.layout.card_content);
        mCardStack.setStackMargin(20);
        mCardStack.setRotateCardDeck(true);

        //
        mCardAdapter = new CardsDataAdapter(getApplicationContext(),0);
        mCardAdapter.add("test1");
        mCardAdapter.add("test2");
        mCardAdapter.add("test3");
        mCardAdapter.add("test4");
        mCardAdapter.add("test5");

        mCardStack.setAdapter(mCardAdapter);
        mCardStack.setListener(new CardStack.CardEventListener() {
            @Override
            public boolean swipeEnd(int section, float distance) {
                return (distance>300)? true : false;
            }

            @Override
            public boolean swipeStart(int section, float distance) {
                return true;
            }

            @Override
            public boolean swipeContinue(int section, float distanceX, float distanceY) {
                return true;
            }

            @Override
            public void discarded(int mIndex, int direction) {
                Log.d("MyActivity", "Card index : " + mIndex + " discarded, curr index is : " + mCardStack.getCurrIndex());
            }

            @Override
            public void topCardTapped() {
                Log.d("MyActivity", "Top card tapped, index is : " + mCardStack.getCurrIndex());
            }
        });
    }

    public void agregarTag(View view) {
        mCardAdapter.add("test6");
    }

    public void irAlTag(View view) {
        mCardStack.gotoCard(3, true);
    }

    public void facturarTag(View view) {
        Log.d("MyActivity", "Facturar img button click, position : " + mCardStack.getCurrIndex());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
