package com.tcg.colorlerp;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.tcg.colorlerp.input.MyInput;
import com.tcg.colorlerp.input.MyInputProcessor;

public class MyGdxGame extends ApplicationAdapter {

	public static final int WORLD_WIDTH = 1280;
	public static final int WORLD_HEIGHT = 720;

	private final float SLIDER_HEIGHT = 15f;

	private ShapeRenderer sr;
	private SpriteBatch sb;
	private Viewport viewport;

	private static final Color C1 = Helpers.color(255, 0, 0, 255);
	private static final Color C2 = Helpers.color(0, 0, 255, 255);

	private MySlider lerpSlider;

	private int lerpAlgo;

	private Text algorithmText;
	private Text colorText;
	private Text percentText;

	@Override
	public void create () {
		viewport = new StretchViewport(WORLD_WIDTH, WORLD_HEIGHT);
		sr = new ShapeRenderer();
		sb = new SpriteBatch();
		Gdx.input.setInputProcessor(new MyInputProcessor());
		lerpSlider = new MySlider(WORLD_WIDTH * 0.25f, (WORLD_HEIGHT * 0.15f) - (SLIDER_HEIGHT * 0.5f), WORLD_WIDTH * 0.5f, SLIDER_HEIGHT);
		lerpAlgo = 0;
		algorithmText = new Text();
		algorithmText.setAlign((byte) (Text.BOTTOM | Text.CENTER));
		colorText = new Text();
		colorText.setAlign(Text.MIDDLE_CENTER);
		percentText = new Text();
		percentText.setAlign(Text.BOTTOM_RIGHT);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		lerpSlider.handleInput(viewport);
		if(MyInput.keyPressed(MyInput.NEXT)) {
			lerpAlgo = MathUtils.clamp(lerpAlgo + 1, 0, LerpAlgoritms.values().length - 1);
		}
		if(MyInput.keyPressed(MyInput.PREV)) {
			lerpAlgo = MathUtils.clamp(lerpAlgo - 1, 0, LerpAlgoritms.values().length - 1);
		}

		LerpAlgoritms algorithm = LerpAlgoritms.values()[lerpAlgo];

		algorithmText.setText(algorithm);
		algorithmText.setPos(WORLD_WIDTH * 0.5f, (WORLD_HEIGHT * 0.75f) + 10);

		Color c = lerp(algorithm, C1, C2, lerpSlider.percentage());
		colorText.setText(c);
		colorText.setPos(WORLD_WIDTH * 0.5f, WORLD_HEIGHT * 0.5f);

		percentText.setText(String.format("%.2f", lerpSlider.percentage() * 100));
		percentText.setPos(lerpSlider.getPos().x - 20, lerpSlider.getPos().y);

		sr.begin(ShapeRenderer.ShapeType.Filled);
		lerpSlider.draw(sr);
		sr.setColor(c);
		sr.rect(WORLD_WIDTH * 0.25f, WORLD_HEIGHT * 0.25f, WORLD_WIDTH * 0.5f, WORLD_HEIGHT * 0.5f);
		sr.end();

		sb.begin();
		sb.setProjectionMatrix(viewport.getCamera().combined);
		algorithmText.draw(sb);
		colorText.draw(sb);
		percentText.draw(sb);
		sb.end();

		viewport.apply(true);
		MyInput.update();
	}

	private Color lerp(LerpAlgoritms lerpAlgoritm, Color c1, Color c2, float percentage) {
		return lerpAlgoritm.lerpAlgo.lerp(c1, c2, percentage);
	}

	@Override
	public void resize(int width, int height) {
		viewport.update(width, height);
	}

	@Override
	public void dispose () {
		sr.dispose();
	}
}
