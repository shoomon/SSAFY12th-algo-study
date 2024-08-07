N = int(input())

# try3 : 규칙성 발견 N의 배수 항상 N의 약수를 가짐

res = 0
for i in range(1, N+1):
    res += N//i * i
print(res)



# # try2 : 1부터 n의 제곱근까지만 0으로 나누어 떨어지는지 확인 : 약수는 짝을 가지고 있기 떄문에
# ## 시간초과 O(NlogN)

# lst = [1, 3, 4]

# for n in range(4, N+1):
#     # 약수가 1일 경우 + 자신일 경우
#     tmp = 1+n
#     half = n**(1/2)
#     for i in range(2, int(half)+1):
#         if n % i == 0 :
#             tmp += i
#             if n // i != i:
#              tmp += (n // i)
#             # print(f'sqrt={half},{int(half)}i={i},n={n//i}')
#     # print(tmp)
#     lst.append(tmp)
# # print(lst)
# print(sum(lst))


## try1 : 1부터 n까지 다 나눠서 약수를 찾음
### 시간초과 O(N^2)
# lst = []
# lst.append(1)
# if N > 1:
#     for n in range(2, N+1):
#         tmp = 0
#         for i in range(1, N+1):
#             if n % i == 0 :
#                 tmp += i
#         lst.append(tmp)
# print(lst)
# print(sum(lst))