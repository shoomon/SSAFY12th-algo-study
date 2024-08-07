# 유클리드 호제법

# a와 b를 나눠서 나오는 나머지를 나누는 수에서 다시 나눔 - 나머지가 0이 될 때, 나누는 수가 최대공약수
a, b = map(int,input().split())
quotient = a
res, mod = b, b
while res != 0:
    mod = res
    res = quotient % mod
    quotient = mod

print(mod)
print(a*b//mod)