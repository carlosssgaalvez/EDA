import Test.QuickCheck

suma :: Int -> Int -> Int
suma x y = x + y
 

factorial :: Integer -> Integer
factorial 0 = 1
factorial n | n > 0 = n * factorial (n-1)
factorial n = error "No se pueden calcular numeros negativos"

-- Num es una clase que engloba a los numeros (Integer, Double, ...)
twice :: Num a => a -> a
twice x = x*x

max' :: Ord a => a -> a -> a
max' x y 
    | x >= y     = x
    | otherwise = y

reciprocal :: (Eq a, Fractional a) => a -> a
reciprocal x 
    | x /= 0 = 1/x
    | otherwise = error "reciprocal no está definida para el 0"

resto :: (Ord a, Num a) => a -> a -> a
resto x y
    | x < y = x
    | otherwise = resto (x-y) y

cociente :: (Ord a, Num a) => a -> a -> a 
cociente x y 
    | x < y = 0
    | otherwise = 1 + cociente(x-y) y

square :: Num  a => a -> a
square x = x*x

--Propiedades que puede o no cumplir la funcion square
-- Ejemplo(p1::Int->Int->Property)
p1 x y = True ==> square (x+y) == square x + square y + 2*x*y

p2 x y = True ==> abs (x+y) == abs x + abs y

p3 x y = x > 0 && y > 0 ==> abs (x+y) == abs x + abs y

sumaDigitos :: Integral a => a -> a
sumaDigitos n
    | n < 10 = n
    | otherwise = r + sumaDigitos c
        where (c,r) = divMod n 10

esAmigo :: Integral a => a -> Bool
esAmigo n 
    | n < 10 = n == 1
    | otherwise = esAmigo(sumaDigitos n)


ejercicio3 :: (Eq a, Ord b) => a -> a -> b -> b -> Bool
ejercicio3 x y z t 
    | z < t = x == y
    | otherwise = False

ackermann :: Integral a => a -> a -> a
ackermann m n
    | m == 0 = n+1
    | m > 0 && n == 0 = ackermann (m-1) 1
    | m > 0 && n > 0 = ackermann (m-1) (ackermann m (n-1))

cerosUnos :: Integer -> (Integer, Integer)
cerosUnos n
    | n == 0 = (1,0)
    | n == 1 = (0,1)

