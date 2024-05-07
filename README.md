# Tugas Kecil 3 IF2211 Strategi Algoritma
Tugas Kecil 3 IF2211 Strategi Algoritma Semester II tahun 2023/2024 Penyelesaian Permainan Word Ladder Menggunakan Algoritma UCS, Greedy Best First Search, dan A*
Dibuat oleh  :   
| Hugo Sabam Augusto | 13522129 |
| :---: | :---: |

## Tech

**Language:** Java\
**Version:** "20.0.2" 2023-07-18\
Java(TM) SE Runtime Environment (build 20.0.2+9-78)


## Installation

1. Pertama, Clone Repository ini : `https://github.com/miannetopokki/Tucil3_13522129.git`
2. Apabila ingin meng-compile dan run, pastikan di directory root lalu jalankan `./run.bat`


## How To Use
Terdapat 2 kamus data yang sudah disediakan : `asisten` dan `default`

Kamus `asisten` berisi kumpulan kata dari file `oracle.txt`

Kamus `default` berisi kumpulan kata dari file `words_alpha.txt`

Berikut contoh input dan output:
```
Kata awal: 
(cat)
Kata tujuan: 
(dog)
-default
-asisten 
Mau pakai kamus folder apa?
(default)
Mau Pakai algoritma apa?
1. UCS
2. Greedy-Best First Search
3. AStar
4. Semuanya
(4)
```
```
====SOLUSI UCS====
cat-> dat-> dot-> dog
Banyak step : 3 step
Waktu : 470 ms
Waktu : 0.47 detik
Smpul yang dieksplor: 617
====SOLUSI GBFS====
cat-> dat-> dot-> dog
Banyak step : 3 step
Waktu : 4 ms
Waktu : 0.004 detik
Smpul yang dieksplor: 4
====SOLUSI AStar====
cat-> dat-> dot-> dog
Banyak step : 3 step
Waktu : 3 ms
Waktu : 0.003 detik
Smpul yang dieksplor: 7
```