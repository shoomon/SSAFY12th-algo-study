count = int(input())
numbers = list(map(int,input().split()))

# 짝수
if count % 2 == 0:
    print(min(numbers) * max(numbers))
# 홀수
elif count == 1:
    print(numbers[0]**2)
else :
    print(min(numbers) * max(numbers))