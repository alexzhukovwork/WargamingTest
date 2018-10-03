package com.my.game.game_object;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.my.game.game_object.Cell;
import com.my.game.screen.GameScreen;
import com.my.game.type.BlockType;
import com.my.game.type.FreeType;
import com.my.game.type.ICellType;
import com.my.game.type.OrangeType;
import com.my.game.type.RedType;
import com.my.game.type.YellowType;

import java.util.Random;

public class Field {
    private Cell[][] cells;
    private Cell [] headerColors;
    private int currentI, currentJ;
    private int prevI, prevJ;
    private boolean isEnter;

    private float height, width;
    private float marginX, marginY;
    private float marginHeaderX, marginHeaderY;
    private final int FIELD_SIZE = 5;
    private final int COLOR_COUNT = 3;

    private ICellType freeType, blockType, redType, yellowType, orangeType;
    private int countRed, countYellow, countOrange;

    public Field() {
        currentI = 0;
        currentJ = 0;
        countOrange = countYellow = countRed = 0;

        cells = new Cell[FIELD_SIZE][FIELD_SIZE];
        headerColors = new Cell[COLOR_COUNT];

        isEnter = false;

        initType();
        calculateSize();
        calculateMargin();
        calculateMarginHeader();
        generateCells();
        generateHeaderColors();
    }

    public void update(float delta) {

    }

    public void moveUp() {
        if (currentI != 0)
            currentI--;
    }

    public void moveDown() {
        if (currentI != FIELD_SIZE - 1)
            currentI++;
    }

    public void moveRight() {
        if (currentJ != FIELD_SIZE - 1)
            currentJ++;
    }

    public void moveLeft() {
        if (currentJ != 0)
            currentJ--;
    }

    public void onClickEnter() {
        if (!isEnter) {
            String type = cells[currentI][currentJ].getCellType().getType();
            if ( !(type.equals(blockType.getType())
                    || type.equals(freeType))
                    ) {
                isEnter = true;
                prevI = currentI;
                prevJ = currentJ;
            }
        }
        else {
            isEnter = false;
            changeCellObject();
        }
    }

    public void restart() {
        generateCells();
        generateHeaderColors();
    }

    public void draw(ShapeRenderer shapeRenderer, float runTime) {
        shapeRenderer.setColor(0, 0, 0, 0);

        for (int i = 0; i < FIELD_SIZE; i++) {
            for (int j = 0; j < FIELD_SIZE; j++) {
                cells[i][j].drawBoard(shapeRenderer);
            }
        }

        shapeRenderer.end();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        for (int i = 0; i < FIELD_SIZE; i++) {
            for (int j = 0; j < FIELD_SIZE; j++) {
                cells[i][j].drawTexture(shapeRenderer);
            }
        }

        for (int i = 0; i < COLOR_COUNT; i++)
            headerColors[i].drawTexture(shapeRenderer);

        shapeRenderer.end();
        Gdx.gl.glLineWidth(5);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);

        shapeRenderer.setColor(1, 0, 0, 0);
        cells[currentI][currentJ].drawAsCurrent(shapeRenderer);

        if (isEnter) {
            shapeRenderer.setColor(0, 1, 0, 0);
            cells[prevI][prevJ].drawAsCurrent(shapeRenderer);
        }

        shapeRenderer.end();
        Gdx.gl.glLineWidth(1);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);

    }

    public boolean isWin() {
        String type;
        ICellType types [] = new ICellType[COLOR_COUNT];
        boolean win = true;

        for (int i = 0; i < COLOR_COUNT; i++) {
            type = headerColors[i].getCellType().getType();

            if (type.equals( redType.getType() ))
                types[i] = redType;
            else if (type.equals( yellowType.getType() ))
                types[i] = yellowType;
            else
                types[i] = orangeType;
        }

        for (int i = 0; i < COLOR_COUNT; i++) {
            for (int j = 0; j < FIELD_SIZE; j++) {
                if (!cells[j][i + 1 * i].getCellType().getType().equals( types[i].getType() ))
                    win = false;
            }
        }

        return win;
    }

    private void calculateSize() {
        if (GameScreen.height > GameScreen.width)
            width = GameScreen.width / 10.0f;
        else {
            width = GameScreen.height / 10.0f;
        }

        height = width;
    }

    private void generateCells() {
        Random random = new Random();
        countOrange = countYellow = countRed = 0;

        for (int i = 0; i < FIELD_SIZE; i++) {
            for (int j = 0; j < FIELD_SIZE; j += 2) {
                cells[i][j] = new Cell(marginX +  j*width, marginY + i*height,
                        width, height,
                        getTypeFromRandom(random.nextInt(3), random, FIELD_SIZE)
                );
            }
        }

        for (int i = 0; i < FIELD_SIZE; i++) {
            for (int j = 1; j < FIELD_SIZE; j += 2) {
                cells[i][j] = new Cell(marginX +  j*width, marginY + i*height,
                        width, height,
                        i % 2 == 0 ? blockType : freeType
                );
            }
        }

    }

    private void generateHeaderColors() {
        Random random = new Random();
        countOrange = countYellow = countRed = 0;

        for (int i = 0; i < COLOR_COUNT; i++)
            headerColors[i] = new Cell(marginHeaderX + (i + i * 1) * width,
                    marginHeaderY, width, height, getTypeFromRandom(random.nextInt(), random, 1));
    }

    private ICellType getTypeFromRandom(int rand, Random random, int n) {
        ICellType type = null;

        switch (rand) {
            case 0:
                if (countRed < n) {
                    countRed++;
                    type = redType;
                }
                break;
            case 1:
                if (countYellow < n) {
                    countYellow++;
                    type = yellowType;
                }
                break;
            case 2:
                if (countOrange < n) {
                    countOrange++;
                    type = orangeType;
                }
                break;
        }

        if (type == null)
            return getTypeFromRandom(random.nextInt(3), random, n);

        return type;
    }

    private void changeCellObject() {
        Cell current = cells[currentI][currentJ];
        Cell prev = cells[prevI][prevJ];

        int diffI = Math.abs(currentI - prevI);
        int diffJ = Math.abs(currentJ - prevJ);

        if (diffI != diffJ
                && diffI < 2
                && diffJ < 2
                && current.getCellType().getType().equals(freeType.getType())
                && !prev.getCellType().getType().equals(blockType.getType())
                ) {

                current.setCellType(prev.getCellType());
                prev.setCellType(freeType);
        }
    }

    private void initType() {
        freeType = new FreeType();
        blockType = new BlockType();
        redType = new RedType();
        yellowType = new YellowType();
        orangeType = new OrangeType();
    }

    private void calculateMargin() {
        marginX = GameScreen.width / 2 - (width * 2.5f);
        marginY = GameScreen.height / 2 - (width * 2.5f);
    }

    private void calculateMarginHeader() {
        marginHeaderX = GameScreen.width / 2 - (width * 2.5f);
        marginHeaderY = GameScreen.height / 2 - (width * 4f);
    }
}
