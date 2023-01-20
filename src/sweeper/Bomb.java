package sweeper;

class Bomb {
    /**
     * Класс методов для нижнего слоя игры
     * Записаны цифры от 0 до 8 - сколько бомб вокруг
     * 9 - сама бомба
     */

    private Matrix bombMap;
    private int totalBombs;

    Bomb(int totalBombs) {
        this.totalBombs = totalBombs;
        fixBombsCount();
    }

    void start () {
        bombMap = new Matrix (Box.ZERO);
        for (int j = 0; j < totalBombs; j++) {
            placeBomb();
        }
    }

    Box get(Coord coord) {
        return bombMap.get(coord);
    }


    //Фиксированное значение бомб, можно убрать "/2"
    private void fixBombsCount () {
        int maxBombs = Ranges.getSize().x * Ranges.getSize().y / 2;
        if (totalBombs > maxBombs) {
            totalBombs = maxBombs;
        }
    }

    private void placeBomb() {
        while (true) {
            Coord coord = Ranges.getRandomCoord();
            if (Box.BOMB == bombMap.get(coord)){
                continue;
            }
            bombMap.set(coord, Box.BOMB);
            incNumbersAroundBomb(coord);
            break;
        }
    }


    private void incNumbersAroundBomb (Coord coord) {
        for(Coord around : Ranges.getCoordsAround(coord)) {
            if (Box.BOMB != bombMap.get(around)) {
                bombMap.set(around, bombMap.get(around).getNextNumberBox());
            }
        }
    }

    int getTotalBombs() {
        return totalBombs;
    }
}
