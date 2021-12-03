class Kettle(object):
    def __init__(self, make, price):
        self.make = make
        self.price = price
        self.on = False
    weight = 100
    def switch_on(self):
        self.on = True

kenwood = Kettle("kenwood", 8.99)
print(kenwood.make)
print(kenwood.price)

kenwood.price = 12.75
print(kenwood.price)
print(kenwood.weight)