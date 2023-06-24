package algorithm

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.Collections
import kotlin.math.max
import kotlin.math.min

// 시간 복잡도 O(M- 8 * N -8 * 8^2)
val br = BufferedReader(InputStreamReader(System.`in`))
val bw = BufferedWriter(OutputStreamWriter(System.out))

var board: MutableList<List<Char>> = mutableListOf()
var chessBoardWhenStartWhite: MutableList<CharArray> = mutableListOf<CharArray>().apply {
    repeat(8){
        add(CharArray(8))
    }
}
var chessBoardWhenStartBlack: MutableList<CharArray> = mutableListOf<CharArray>().apply {
    repeat(8){
        add(CharArray(8))
    }
}
const val BLACK = 'B'
const val WHITE = 'W'
var ans = 2501
fun main() {
    val (n, m) = br.readLine().split(" ").map {
        it.toInt()
    }
    repeat(n){
        board.add(br.readLine().toList())
    }
    selectChessBoard(n, m)
    bw.write("$ans")
    bw.flush()
    br.close()
    bw.close()
}

fun selectChessBoard(n: Int, m: Int) {
    for (i in 0 .. n - 8){
        for (j in 0 .. m - 8){
            for(k in 0 until 8){
                for(l in 0 until 8){
                    chessBoardWhenStartWhite[k][l] = board[i+k][j+l]
                    chessBoardWhenStartBlack[k][l] = board[i+k][j+l]
                }
            }
            var cntWhenStartWhite = 0
            var cntWhenStartBlack = 0
            if (board[i][j] == BLACK){
                chessBoardWhenStartWhite[0][0] = WHITE
                cntWhenStartWhite++
            }else{
                chessBoardWhenStartBlack[0][0] = BLACK
                cntWhenStartBlack++
            }
            cntWhenStartBlack += paint(chessBoardWhenStartBlack)
            cntWhenStartWhite += paint(chessBoardWhenStartWhite)
            ans = min(ans, min(cntWhenStartWhite, cntWhenStartBlack))
        }
    }
}

fun paint(chessBoard: MutableList<CharArray> ): Int {
    var cnt = 0
    val dx = listOf(1, 0)
    val dy = listOf(0, 1)
    for(x in 0 until 8){
        for(y in 0 until 8){
            val curColor = chessBoard[x][y]
            repeat(2) {i ->
                val nx = x + dx[i]
                val ny = y + dy[i]
                if(0 <= nx && nx < 8 && 0 <= ny && ny < 8){
                    if(curColor == BLACK && chessBoard[nx][ny] != WHITE){
                        chessBoard[nx][ny] = WHITE
                        cnt++
                    }else if(curColor == WHITE && chessBoard[nx][ny] != BLACK){
                        chessBoard[nx][ny] = BLACK
                        cnt++
                    }
                }
            }
        }
    }
    return cnt
}