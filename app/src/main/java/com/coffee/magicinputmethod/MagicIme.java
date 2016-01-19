package com.coffee.magicinputmethod;

import android.inputmethodservice.InputMethodService;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;

public class MagicIme extends InputMethodService {
	private KeyboardView mKeyboardView;
	private static final String TAG = "MagicIme";

	@Override
	public void onCreate() {
		super.onCreate();
	}

	@Override
	public void onInitializeInterface() { 
		Log.d(TAG, "onInitializeInterface");
	}

	@Override
	public View onCreateInputView() {
		Log.d(TAG, "onCreateInputView");
		mKeyboardView = (KeyboardView) getLayoutInflater().inflate(
				R.layout.input, null);
		Keyboard keyboard = new Keyboard(this, R.xml.custom_keyboard);
		mKeyboardView.setKeyboard(keyboard);
		return mKeyboardView;
	}

	@Override
	public View onCreateCandidatesView() {
		Log.d(TAG, "onCreateCandidatesView");
		return null;
	}

	@Override
	public void onStartInputView(EditorInfo attribute, boolean restarting) {
		super.onStartInputView(attribute, restarting);
		Log.d(TAG, "onStartInputView");

		mKeyboardView.setOnKeyboardActionListener(new KeyboardView.OnKeyboardActionListener() {
			@Override
			public void onPress(int primaryCode) {

			}

			@Override
			public void onRelease(int primaryCode) {

			}

			@Override
			public void onKey(int primaryCode, int[] keyCodes) {
				switch (primaryCode) {
					case Constant.textCode1:
						getCurrentInputConnection().commitText("aaa", 0);
						break;
					case Constant.textCode2:
						getCurrentInputConnection().commitText("bbb", 0);
						break;
					case Constant.delCode:
						keyDownUp(KeyEvent.KEYCODE_DEL);
						break;
					case Constant.cancelCode:
						handleClose();
						break;
					case Constant.changeInputMethod:
						((InputMethodManager) getApplicationContext()
								.getSystemService(INPUT_METHOD_SERVICE))
								.showInputMethodPicker();
						break;
					default:
						break;
				}

			}

			@Override
			public void onText(CharSequence text) {

			}

			@Override
			public void swipeLeft() {

			}

			@Override
			public void swipeRight() {

			}

			@Override
			public void swipeDown() {

			}

			@Override
			public void swipeUp() {

			}
		});

	}

	/**
	 * Helper to send a key down / key up pair to the current editor.
	 */
	private void keyDownUp(int keyEventCode) {
		getCurrentInputConnection().sendKeyEvent(
				new KeyEvent(KeyEvent.ACTION_DOWN, keyEventCode));
		getCurrentInputConnection().sendKeyEvent(
				new KeyEvent(KeyEvent.ACTION_UP, keyEventCode));
	}

	private void handleClose() {
		requestHideSelf(0);
		mKeyboardView.closing();
	}

}
