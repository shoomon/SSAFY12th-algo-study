def is_decimal(n):
    is_decimal = True
    for i in range(2, n):
        if n % i == 0:
            is_decimal = False
            break
    return is_decimal

# 1과 자기자신 제외한 나머지 수로 나눔으로써, 인수 찾기 - 있으면 소수가 아님

N = int(input())
lst = list(map(int,input().split()))
cnt = 0
for l in lst:
    if l == 1:
        pass
    elif is_decimal(l)==True:
        cnt+=1

print(cnt)

            

