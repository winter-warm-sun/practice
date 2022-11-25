import java.util.*;

// 注意类名必须为 Main, 不要有任何 package xxx 信息
class Node {
    int x;
    int y;
    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int row = in.nextInt();
        int col = in.nextInt();
        int[][] map = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                map[i][j] = in.nextInt();
            }
        }
        //搜索最短路径
        ArrayList<Node> path = new ArrayList<>();
        ArrayList<Node> minPath = new ArrayList<>();
        int[][] book = new int[row][col];
        getMinPath(map, row, col, 0, 0, book, path, minPath);
//打印最短路径
        for (Node n : minPath) {
            System.out.println("(" + n.x + "," + n.y + ")");
        }
    }
    //mat: 迷宫矩阵, row, col
//x, y: 当前位置
//book: 标记矩阵，标记当前位置是否走过
//path: 保存当前路径的每一个位置
//minPath: 保存最短路径
    public static void getMinPath(int[][] mat, int row, int col,
                                  int x, int y, int[][] book, ArrayList<Node> path, ArrayList<Node> minPath) {
//判断(x,y): 是否越界，是否走过，是否有障碍
        if (x < 0 || x >= row || y < 0 || y >= col
                || book[x][y] == 1 || mat[x][y] == 1)
            return;
//把当前位置存入路径中
        path.add(new Node(x, y));
//标记当前位置
        book[x][y] = 1;
//判断当前位置是否为出口
        if (x == row - 1 && y == col - 1) {
//一条新的路径产生
//判断是否为更短的路径
            if (minPath.isEmpty() || path.size() < minPath.size()) {
//更新更短路径
                minPath.clear();
                for (Node n : path)
                    minPath.add(n);
            }
        }
//继续搜索(x,y)的上下左右四个方向
        getMinPath(mat, row, col, x + 1, y, book, path, minPath);
        getMinPath(mat, row, col, x - 1, y, book, path, minPath);
        getMinPath(mat, row, col, x, y - 1, book, path, minPath);
        getMinPath(mat, row, col, x, y + 1, book, path, minPath);
//把当前位置从路径中删除，寻找新的路径
        path.remove(path.size() - 1);
        book[x][y] = 0;
    }
}