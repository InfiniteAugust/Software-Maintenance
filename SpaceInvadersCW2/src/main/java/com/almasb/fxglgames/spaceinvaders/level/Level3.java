package com.almasb.fxglgames.spaceinvaders.level;

import static com.almasb.fxgl.app.DSLKt.geti;
import static com.almasb.fxglgames.spaceinvaders.Config.ENEMIES_PER_ROW;
import static com.almasb.fxglgames.spaceinvaders.Config.ENEMY_ROWS;

import com.almasb.fxgl.entity.GameEntity;


public class Level3 extends SpaceLevel{

	@Override
	public void init() {
            for (int x = 0; x < ENEMIES_PER_ROW*4; x++) {
            			GameEntity enemy = spawnEnemy(x * 40, 150 + 150 * geti("level"));
            }
	}

	@Override
	public void destroy() {
	}

}
