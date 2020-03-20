import pycuda.gpuarray as gpuarray
import pycuda.curandom as rand
import pycuda.driver as cuda
import pycuda.autoinit
import time
import numpy

def find_pi_cuda(n):
    '''Takes in integer n (n points used for integration) '''

        # here's Monte Carlo for finding pi
    seed = rand.seed_getter_unique(n)
    x = rand.XORWOWRandomNumberGenerator(seed, 0)
    y = rand.XORWOWRandomNumberGenerator(seed, 0)
    ones = gpuarray.ones(int(n)) # need an array the size of guesses to use mask
    distance = np.hypot(x,y)
    mask = np.where(distance <= 1.0)
    pi = 4*np.sum(ones[mask])/n
    error = np.abs(np.pi - pi)

    return pi, error


start = time.time()
test = np.linspace(0, 20, 21)
test = 2**test.astype(int)
error2 = gpuarray.array([])
for i in test:
    pi, error = find_pi_cuda(i)
    error2 = np.append(error2, error)

stop = time.time()

print("Time to run:", stop - start, "seconds; pi is", pi, "and error is", error)
