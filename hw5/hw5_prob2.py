import pycuda.gpuarray as gp
import pycuda.curandom as rand
import pycuda.driver as cuda
import pycuda.autoinit
import time
import numpy

def circle_time(x_and_y):

    return x_and_y[:,0]**2 + x_and_y[:,1]**2 < 1


def find_pi_cuda(function, n, x_lim=[-1,1],y_lim=[-1,1]):
    '''Takes in integer n (n points used for integration), does Monte Carlo to find pi using a mask. Returns int pi'''

    # here's Monte Carlo for finding pi
    x_side = x_lim[1]-x_lim[0]
    y_side = y_lim[1]-y_lim[0]
    Volume_boi = x_side*y_side

    # x and y
    rg = rand.XORWOWRandomNumberGenerator()
    points = rg.gen_uniform((n,2), np.float32)
    points[:,0] = points[:,0]*x_side+x_lim[0]
    points[:,1] = points[:,1]*y_side+y_lim[0]
    pibois = function(points)

    return gp.sum(pibois)/n*V


n = 2**8
start = time.time()
pi = find_pi_cuda(circle_time,n)
stop = time.time()

print("Time to run:", stop - start, "seconds; pi is", pi)
