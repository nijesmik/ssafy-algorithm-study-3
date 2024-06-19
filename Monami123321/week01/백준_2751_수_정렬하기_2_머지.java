package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Collectors;

public class 백준_2751_수_정렬하기_2_머지 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] arr = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		mergeSort(arr);
		System.out.println(Arrays.stream(arr).mapToObj(String::valueOf).collect(Collectors.joining("\n")));
		br.close();
	}

	static void mergeSort(int[] arr) {
		int[] tmp = new int[arr.length];
		sort(arr, tmp, 0, arr.length - 1);
	}

	static void sort(int[] arr, int[] tmp, int left, int right) {
		if (left == right) {
			return;
		}

		int mid = (left + right) >> 1;
		sort(arr, tmp, left, mid);
		sort(arr, tmp, mid + 1, right);
		merge(arr, tmp, left, mid, right);
	}

	static void merge(int[] arr, int[] tmp, int left, int mid, int right) {
		int l = left;
		int r = mid + 1;
		int index = left;
		while (l <= mid && r <= right) {
			if (arr[l] <= arr[r]) {
				tmp[index++] = arr[l++];
			} else {
				tmp[index++] = arr[r++];
			}
		}
		while (l <= mid) {
			tmp[index++] = arr[l++];
		}
		while (r <= right) {
			tmp[index++] = arr[r++];
		}
		System.arraycopy(tmp, left, arr, left, right - left + 1);
	}
}
