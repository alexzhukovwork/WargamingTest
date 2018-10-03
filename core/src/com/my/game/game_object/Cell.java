package com.my.game.game_object;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.my.game.type.ICellType;

public class Cell {
    private Rectangle rectangle;
    private ICellType cellType;

    public Cell(float x, float y, float width, float height, ICellType cellType) {
        rectangle = new Rectangle(x, y, width, height);
        this.cellType = cellType;
    }

    public Vector2 getPosition() {
        return rectangle.getPosition(null);
    }

    public void setPosition(float x, float y) {
        rectangle.setPosition(x, y);
    }

    public void drawBoard(ShapeRenderer shapeRenderer) {
        shapeRenderer.rect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
    }

    public void drawTexture(ShapeRenderer shapeRenderer) {
        Color color  = shapeRenderer.getColor();
        shapeRenderer.setColor(cellType.getColor());
        shapeRenderer.rect(rectangle.x + 10, rectangle.y + 10, rectangle.width - 20, rectangle.height - 20);
        shapeRenderer.setColor(color);
    }

    public void drawAsCurrent(ShapeRenderer shapeRenderer) {
        shapeRenderer.rect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
    }

    public void setCellType(ICellType cellType) {
        this.cellType = cellType;
    }

    public ICellType getCellType() {
        return cellType;
    }
}
