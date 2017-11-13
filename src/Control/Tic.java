package Control;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.Timer;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

@SuppressWarnings("serial")
public abstract class Tic extends Timer {
	Player playMp3;
	FileInputStream fls;

	public Tic(int delay, TicAction listener) {
		super(delay, listener);
	}

	@Override
	public void stop() {
		super.stop();
		timerstop();
		playMp3.close();
	}

	@Override
	public void start() {
		super.start();
		try {

			

			Thread sound = new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						while (true) {
							fls = new FileInputStream("Tetris_BGM.mp3");
							playMp3 = new Player(fls);
							playMp3.play();
						}
					} catch (Exception e) {
					}
				}
			});
			sound.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	abstract public void timerstop();
}
