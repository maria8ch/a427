import pycuda.gpuarray as gp
import pycuda.curandom as rand
import pycuda.driver as cuda
import pycuda.autoinit
import time
import numpy as np


def find_pi_cuda(n):
    '''Takes in integer n (n points used for integration), does Monte Carlo to
    find pi using gpu methods. Returns int pi'''

    #  x and y coordinates generated
    rg = rand.XORWOWRandomNumberGenerator()
    x = rg.gen_uniform(n, np.float32)
    y = rg.gen_uniform(n, np.float32)

    # circle times
    am_circle = x**2 + y**2

    outside = pycuda.gpuarray.zeros_like(x, np.float32)
    inside = pycuda.gpuarray.ones_like(x, np.float32)


    # here's Monte Carlo for finding pi

    H = pycuda.gpuarray.if_positive(am_circle <= 1, inside, outside)
    pi = 4 * gp.sum(H)/n

    return pi


n = 2**8
start = time.time()
pi = find_pi_cuda(n)
stop = time.time()

print("Time to run 2^8 points:", stop - start, "seconds; pi is", pi)

n = 2**16
start = time.time()
pi = find_pi_cuda(n)
stop = time.time()

print("Time to run 2^16 points:", stop - start, "seconds; pi is", pi)

n = 2**24
start = time.time()
pi = find_pi_cuda(n)
stop = time.time()

print("Time to run 2^24 points:", stop - start, "seconds; pi is", pi)

n = 2**29
start = time.time()
pi = find_pi_cuda(n)
stop = time.time()

print("Time to run 2^29 points:", stop - start, "seconds; pi is", pi)

n = 2**30
start = time.time()
pi = find_pi_cuda(n)
stop = time.time()

print("Time to run 2^30 points:", stop - start, "seconds; pi is", pi)

# After some guess and check, 2^29 is the highest it can go without running out
# memory.
