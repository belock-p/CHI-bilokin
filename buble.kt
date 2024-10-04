import kotlin.system.measureTimeMillis
import kotlin.random.Random

// Bubble sort
fun bubbleSort(arr: IntArray) {
    val n = arr.size
    for (i in 0 until n) {
        for (j in 0 until n - i - 1) {
            if (arr[j] > arr[j + 1]) {
                val temp = arr[j]
                arr[j] = arr[j + 1]
                arr[j + 1] = temp
            }
        }
    }
}

// Quick sort
fun quickSort(arr: IntArray): IntArray {
    if (arr.size <= 1) {
        return arr
    }
    val pivot = arr[arr.size / 2]
    val equal = arr.filter { it == pivot }.toIntArray()
    val less = arr.filter { it < pivot }.toIntArray()
    val greater = arr.filter { it > pivot }.toIntArray()
    return quickSort(less) + equal + quickSort(greater)
}

fun main() {
    // 1000 випадкових елементів
    val arr = IntArray(1000) { Random.nextInt(1, 10000) }

    // Вимір часу Bubble sort
    val bubbleSortArray = arr.copyOf()
    val bubbleSortTime = measureTimeMillis {
        bubbleSort(bubbleSortArray)
    }

    // Вимір часу Quick sort
    val quickSortArray = arr.copyOf()
    val quickSortTime = measureTimeMillis {
        quickSort(quickSortArray)
    }

    // Результат
    println("Bubble Sort час: $bubbleSortTime мс")
    println("Quick Sort час: $quickSortTime мс")
}
