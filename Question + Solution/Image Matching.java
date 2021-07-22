import java.util.Set;

public class Solution5 {

static void main(String[] args) {
	List<String> grid1 = new ArrayList<>();
	grid1.add("111");
	grid1.add("100");
	grid1.add("100");
	List<String> grid2 = new ArrayList<>();
	grid2.add("111");
	grid2.add("100");
	grid2.add("101");
	System.out.println(countMatches(grid1, grid2));

}

static int[][] direction = new int[][] { { 0, -1 }, { -1, 0 }, { 1, 0 }, { 0, 1 } };

public static int countMatches(List<String> grid1, List<String> grid2) {
	// Write your code here
	Set<String> list1 = new HashSet<>();
	Set<String> list2 = new HashSet<>();
	boolean[][] visited1 = new boolean[grid1.size()][grid1.get(0).length()];
	boolean[][] visited2 = new boolean[grid1.size()][grid1.get(0).length()];
	for (int i = 0; i < grid1.size(); i++) {
		for (int j = 0; j < grid1.get(i).length(); j++) {
			if (grid1.get(i).charAt(j) == '1' && !visited1[i][j]) {
				visited1[i][j] = true;
				list1.add(dfs(grid1, i, j, visited1, new StringBuilder("(" + i + "," + j + ")")).toString());
			}
			if (grid2.get(i).charAt(j) == '1' && !visited2[i][j]) {
				visited2[i][j] = true;
				list2.add(dfs(grid2, i, j, visited2, new StringBuilder("(" + i + "," + j + ")")).toString());
			}
		}
	}
	int count = 0;
	System.out.println(list1);
	System.out.println(list2);
	for (String s : list1) {
		if (list2.contains(s))
			count++;
	}
	return count;
}

public static StringBuilder dfs(List<String> grid, int row, int col, boolean[][] visited, StringBuilder sb) {
	for (int i = 0; i < 4; i++) {
		int newRow = row + direction[i][0];
		int newCol = col + direction[i][1];
		if (isValid(grid, newRow, newCol) && !visited[newRow][newCol] && grid.get(newRow).charAt(newCol) == '1') {
			sb = sb.append("(" + newRow + "," + newCol + ")");
			visited[newRow][newCol] = true;
			dfs(grid, newRow, newCol, visited, sb);
		}
	}
	return sb;
}

public static boolean isValid(List<String> grid, int i, int j) {
	return i >= 0 && j >= 0 && i < grid.size() && j < grid.get(0).length();
}
}