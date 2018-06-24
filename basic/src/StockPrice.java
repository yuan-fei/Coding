
public class StockPrice {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public static int max_profit_infinite_trans(int[] prices) {
        if(prices == null || prices.length == 0){
            return 0;
        }

        int[] boughtState = new int[prices.length + 1];
        int[] soldState = new int[prices.length + 1];
        boughtState[0] = -prices[0];
        soldState[0] = 0;
        for (int i = 1; i <= prices.length; i++) {
            boughtState[i] = Math.max(boughtState[i - 1], soldState[i - 1] - prices[i - 1]);
            soldState[i] = Math.max(soldState[i - 1], boughtState[i - 1] + prices[i - 1]);
        }

        return soldState[prices.length];
	}
	
    public int max_profit_1_tran(int[] prices) {
        if(prices == null || prices.length == 0){
            return 0;
        }
        int maxProfit = 0;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < prices.length; i++) {
            min = Math.min(min, prices[i]);
            maxProfit = Math.max(maxProfit, prices[i] - min);
        }
        return maxProfit;
    }
	
    private int max_profit_2_trans(int[] prices) {
        if(prices == null || prices.length == 0){
            return 0;
        }

        int[][] buyState = new int[prices.length + 1][3];
        int[][] sellState = new int[prices.length + 1][3];

        for (int i = 0; i <= prices.length; i++) {
            buyState[i][0] = 0;
            sellState[i][0] = 0;
        }

        for (int i = 0; i <= 2; i++) {
            buyState[0][i] = -prices[0];
            sellState[0][i] = 0;
        }


        for (int i = 1; i <= prices.length; i++) {
            for(int j = 1; j <= 2; j++){
                buyState[i][j] = Math.max(buyState[i - 1][j], sellState[i - 1][j - 1] - prices[i - 1]);
                sellState[i][j] = Math.max(sellState[i - 1][j], buyState[i - 1][j] + prices[i - 1]);
            }           
        }

        return sellState[prices.length][2];
    }
	
	public static int max_profit_k_trans(int k, int[] prices) {
		if (k > prices.length / 2) {
			return max_profit_infinite_trans(prices);
		}

		int[][] buyState = new int[prices.length + 1][k + 1];
		int[][] sellState = new int[prices.length + 1][k + 1];

		for (int i = 0; i <= prices.length; i++) {
			buyState[i][0] = 0;
			sellState[i][0] = 0;
		}

		for (int i = 0; i <= k; i++) {
			buyState[0][i] = -prices[0];
			sellState[0][i] = 0;
		}

		for (int j = 1; j <= k; j++) {
			for (int i = 1; i <= prices.length; i++) {
				buyState[i][j] = Math.max(buyState[i - 1][j], sellState[i - 1][j - 1] - prices[i - 1]);
				sellState[i][j] = Math.max(sellState[i - 1][j], buyState[i - 1][j] + prices[i - 1]);
			}
		}

		return sellState[prices.length][k];
	}

	public static int max_profit_infinite_trans_with_cooldown(int[] prices) {
		if (prices == null || prices.length == 0) {
			return 0;
		}
		int[] boughtState = new int[prices.length + 1];
		int[] soldState = new int[prices.length + 1];
		int[] cooldownState = new int[prices.length + 1];

		boughtState[0] = -prices[0];
		soldState[0] = 0;
		cooldownState[0] = 0;

		for (int i = 1; i <= prices.length; i++) {
			boughtState[i] = Math.max(boughtState[i - 1], cooldownState[i - 1] - prices[i - 1]);
			soldState[i] = boughtState[i - 1] + prices[i - 1];
			cooldownState[i] = Math.max(cooldownState[i - 1], soldState[i - 1]);
		}
		return Math.max(soldState[prices.length], cooldownState[prices.length]);
	}

	public static int max_profit_infinite_trans_with_fee(int[] prices, int fee) {
		if (prices == null || prices.length == 0) {
			return 0;
		}
		int[] boughtState = new int[prices.length + 1];
		int[] soldState = new int[prices.length + 1];

		boughtState[0] = -prices[0] - fee;
		soldState[0] = 0;

		for (int i = 1; i <= prices.length; i++) {
			boughtState[i] = Math.max(soldState[i - 1] - prices[i - 1] - fee, boughtState[i - 1]);
			soldState[i] = Math.max(soldState[i - 1], boughtState[i - 1] + prices[i - 1]);
		}
		return soldState[prices.length];
	}

}
