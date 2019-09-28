package com.prj.commom.factory;

import com.prj.commom.BoardNode;
import com.prj.commom.Dice;
import com.prj.entity.PathBoard;
import com.prj.entity.Ground;
import com.prj.entity.Player;

import java.util.ArrayList;
import java.util.Random;

public class BoardSeeder {
    public static int SIZE = 38;
    public static int TOTAL_PROPORTION = 10;
    public static int GROUND_PROPORTION = 7;
    public static long GROUND_PRICE_MULTIPLIER = 100;
    public static int GROUND_PRICE_RANGE = 90;

    public void seedBoard(PathBoard board) {
        Random rand = new Random();
        int i, j;
        i = 0;

        while (i < SIZE) {
            j = rand.nextInt(TOTAL_PROPORTION);

            if (j < GROUND_PROPORTION) {
                BoardNode groundNode = this.generateGround(board.getBanker());
                board.append(groundNode);
            } else {
                BoardNode luckyCardNode = new BoardNode(false);
                board.append(luckyCardNode);
            }

            i++;
        }
    }

    private BoardNode generateGround(Player banker) {
        ArrayList<Integer> prices = new ArrayList<>();
        for (int i = 10; i <= GROUND_PRICE_RANGE; i += 10) {
            prices.add(i);
        }

        int randPrice = prices.get(Dice.nextPositiveInt(prices.size()));
        long price = Dice.nextPositiveInt(randPrice) * GROUND_PRICE_MULTIPLIER;

        Ground ground = new Ground(price, banker);
        return new BoardNode(ground);
    }
}