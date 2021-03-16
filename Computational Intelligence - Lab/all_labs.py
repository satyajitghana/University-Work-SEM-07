#!/usr/bin/env python
# coding: utf-8

# # Computational Intelligence

# ## Lab01
# 
# Implement BFS

# In[6]:


from collections import defaultdict
from queue import Queue
from typing import List, Any


# In[76]:


class Graph:
    def __init__(self):
        self._graph = defaultdict(list)
    
    def add_edge(self, u, v):
        self._graph[u].append(v)
        
    def neighbours(self, u):
        return self._graph[u]
    
    def bfs(self, start):
        t_queue: Queue = Queue()
        d_visited: Dict[Any, List] = {n: False for n in self._graph.keys()}
        
        print(f'BFS, start={start}: [', end="")
        
        t_queue.put(start)
        d_visited[start] = True
        
        while not t_queue.empty():
            node = t_queue.get()
            
            print(f'{node},', end=" ")
            
            for neighbour in self.neighbours(node):
                if not d_visited[neighbour]:
                    t_queue.put(neighbour)
                    d_visited[neighbour] = True
        print("\b\b]")


# In[77]:


graph = Graph()


# In[78]:


graph.add_edge(0, 1)
graph.add_edge(0, 2)
graph.add_edge(1, 2)
graph.add_edge(2, 0)
graph.add_edge(2, 3)
graph.add_edge(3, 3)


# In[79]:


graph._graph


# In[80]:


graph.bfs(2)


# ## Lab02
# 
# Implement DFS

# In[81]:


from collections import defaultdict, deque
from typing import List, Any


# In[82]:


class Graph:
    def __init__(self):
        self._graph = defaultdict(list)
    
    def add_edge(self, u, v):
        self._graph[u].append(v)
        
    def neighbours(self, u):
        return self._graph[u]
    
    def dfs(self, start):
        t_stack: deque = deque()
        d_visited: Dict[Any, List] = {n: False for n in self._graph.keys()}
        path: List[Any] = [] 
        
        t_stack.append(start)
        print(f'DFS, start={start}:', end=" ")
        
        while not (len(t_stack) == 0):
            node = t_stack.pop()
            
            if not d_visited[node]:
                path.append(node)
                d_visited[node] = True
                for neighbour in self.neighbours(node):
                    if not d_visited[neighbour]:
                        t_stack.append(neighbour)
        print(path)


# In[83]:


graph = Graph()


# In[84]:


graph.add_edge(0, 1)
graph.add_edge(0, 2)
graph.add_edge(1, 2)
graph.add_edge(2, 0)
graph.add_edge(2, 3)
graph.add_edge(3, 3)


# In[85]:


graph.dfs(2)


# ## Lab03
# 
# Implement Genetic Algorithm

# In[89]:


import random
import math


# In[90]:


def generate_population(size, x_boundaries, y_boundaries):
    lower_x_boundary, upper_x_boundary = x_boundaries
    lower_y_boundary, upper_y_boundary = y_boundaries

    population = []
    for i in range(size):
        individual = {
            "x": random.uniform(lower_x_boundary, upper_x_boundary),
            "y": random.uniform(lower_y_boundary, upper_y_boundary),
        }
        population.append(individual)

    return population


# In[91]:


def apply_function(individual):
    x = individual["x"]
    y = individual["y"]
    return math.sin(math.sqrt(x ** 2 + y ** 2))


# In[92]:


def choice_by_roulette(sorted_population, fitness_sum):
    offset = 0
    normalized_fitness_sum = fitness_sum

    lowest_fitness = apply_function(sorted_population[0])
    if lowest_fitness < 0:
        offset = -lowest_fitness
        normalized_fitness_sum += offset * len(sorted_population)

    draw = random.uniform(0, 1)

    accumulated = 0
    for individual in sorted_population:
        fitness = apply_function(individual) + offset
        probability = fitness / normalized_fitness_sum
        accumulated += probability

        if draw <= accumulated:
            return individual


# In[93]:


def sort_population_by_fitness(population):
    return sorted(population, key=apply_function)


def crossover(individual_a, individual_b):
    xa = individual_a["x"]
    ya = individual_a["y"]

    xb = individual_b["x"]
    yb = individual_b["y"]

    return {"x": (xa + xb) / 2, "y": (ya + yb) / 2}


def mutate(individual):
    next_x = individual["x"] + random.uniform(-0.05, 0.05)
    next_y = individual["y"] + random.uniform(-0.05, 0.05)

    lower_boundary, upper_boundary = (-4, 4)

    # Guarantee we keep inside boundaries
    next_x = min(max(next_x, lower_boundary), upper_boundary)
    next_y = min(max(next_y, lower_boundary), upper_boundary)

    return {"x": next_x, "y": next_y}


def make_next_generation(previous_population):
    next_generation = []
    sorted_by_fitness_population = sort_population_by_fitness(previous_population)
    population_size = len(previous_population)
    fitness_sum = sum(apply_function(individual) for individual in population)

    for i in range(population_size):
        first_choice = choice_by_roulette(sorted_by_fitness_population, fitness_sum)
        second_choice = choice_by_roulette(sorted_by_fitness_population, fitness_sum)

        individual = crossover(first_choice, second_choice)
        individual = mutate(individual)
        next_generation.append(individual)

    return next_generation


# In[99]:


generations = 100

population = generate_population(size=10, x_boundaries=(-4, 4), y_boundaries=(-4, 4))

i = 1
while True:
    if i == 1 or i % 20 == 0 or i == generations:
        print(f"🧬 GENERATION {i}")

        for individual in population:
            print(individual, apply_function(individual))

    if i == generations:
        break

    i += 1

    population = make_next_generation(population)

best_individual = sort_population_by_fitness(population)[-1]
print("\n🔬 FINAL RESULT")
print(best_individual, apply_function(best_individual))


# ## Lab04
# 
# Implement Hill Climbing

# In[124]:


import numpy as np
import matplotlib.pyplot as plt


# In[121]:


class HillClimbing:
    def __init__(self, init_point):
        self.init_point = np.array(init_point)
        self.best_point = self.init_point.copy()
        self.history = []
        
    def eval_function(self, inp):
        # simple sphere function as `eval_function`
        return np.sqrt(np.dot(inp, inp))
    
    def perform_climb(self, max_iters: int = 100, step_size: int = 2, print_interval = 10):
        for it in range(1, max_iters+1):
            new_point = self.best_point + np.random.randn(*self.best_point.shape) * step_size
            if self.eval_function(new_point) < self.eval_function(self.best_point):
                self.best_point = new_point
                
            if it % print_interval == 0 or it == 1 or it == max_iters:
                print(f'iter: {it}, current best: {self.best_point}, eval_val: {self.eval_function(self.best_point)}')
            self.history.append(self.eval_function(self.best_point))


# In[143]:


hc = HillClimbing(init_point = np.array([10, 10]))


# In[144]:


hc.perform_climb(max_iters=5000, step_size=0.01, print_interval=200)


# In[145]:


plt.plot(hc.history)
plt.xlabel('iterations')
plt.ylabel('loss')


# ## Lab05
# 
# Implement MinMax - Alpha Beta Pruning Algorithm

# In[176]:


import numpy as np


# In[177]:


def minmax(maximizing_player, values):
    alpha = -np.inf
    beta = np.inf
    
    def _minmax(depth, node_idx, maximizing_player, values, alpha, beta):
        
        if depth == 3:
            return values[node_idx]
        
        if maximizing_player:
            
            best = -np.inf
            
            # left and right children
            for i in range(0, 2):
                val = _minmax(depth + 1, node_idx * 2 + i, False, values, alpha, beta)
                
                best = max(best, val)
                alpha = max(alpha, best)
                
                if beta <= alpha:
                    break
                    
            return best
        
        else:
            
            best = np.inf
            
            # left and right children
            for i in range(0, 2):
                val = _minmax(depth + 1, node_idx * 2 + i, True, values, alpha, beta)
                
                best = min(best, val)
                beta = min(beta, best)
                
                if beta <= alpha:
                    break
                    
            return best 
    return _minmax(depth=0, node_idx=0, maximizing_player=maximizing_player, values=values, alpha=alpha, beta=beta)


# In[179]:


values = [3, 5, 6, 9, 1, 2, 0, -1]

optimal_val = minmax(maximizing_player=True, values=values)

print(f'values: {values}')
print(f'optimal value: {optimal_val}')


# ## Lab06
# 
# Implement Fuzzy Logic

# In[6]:


import numpy as np
import skfuzzy as fuzz
from skfuzzy import control as ctrl


# In[7]:


# New Antecedent/Consequent objects hold universe variables and membership
# functions
quality = ctrl.Antecedent(np.arange(0, 11, 1), 'quality')
service = ctrl.Antecedent(np.arange(0, 11, 1), 'service')
tip     = ctrl.Consequent(np.arange(0, 26, 1), 'tip')

# Auto-membership function population is possible with .automf(3, 5, or 7)
quality.automf(3)
service.automf(3)

# Custom membership functions can be built interactively with a familiar,
# Pythonic API
tip['low']    = fuzz.trimf(tip.universe, [0, 0, 13])
tip['medium'] = fuzz.trimf(tip.universe, [0, 13, 25])
tip['high']   = fuzz.trimf(tip.universe, [13, 25, 25])


# In[8]:


quality['average'].view()


# In[13]:


service.view()


# In[14]:


tip.view()


# In[15]:


rule1 = ctrl.Rule(quality['poor'] | service['poor'], tip['low'])
rule2 = ctrl.Rule(service['average'], tip['medium'])
rule3 = ctrl.Rule(service['good'] | quality['good'], tip['high'])

rule1.view()


# In[16]:


tipping_ctrl = ctrl.ControlSystem([rule1, rule2, rule3])


# In[17]:


tipping = ctrl.ControlSystemSimulation(tipping_ctrl)


# In[18]:


# Pass inputs to the ControlSystem using Antecedent labels with Pythonic API
# Note: if you like passing many inputs all at once, use .inputs(dict_of_data)
tipping.input['quality'] = 6.5
tipping.input['service'] = 9.8

# Crunch the numbers
tipping.compute()


# In[19]:


print(tipping.output['tip'])
tip.view(sim=tipping)


# ## Lab07
# 
# Implement Neural Network

# In[118]:


import numpy as np
import matplotlib.pyplot as plt
from matplotlib import cm


# In[104]:


class NeuralNet:
    def __init__(self, w_init, b_init):
        self.w = w_init
        self.b = b_init
        self.w_h = []
        self.b_h = []
        self.e_h = []
        
    def sigmoid(self, x, w=None, b=None):
        w = self.w if w is None else w
        b = self.b if b is None else b
        
        return 1. / (1. + np.exp(-(w * x + b)))
    
    def error(self, X, Y, w=None, b=None):
        w = self.w if w is None else w
        b = self.b if b is None else b
        
        err = 0
        for x, y in zip(X, Y):
            err += 0.5 * (self.sigmoid(x, w, b) - y) ** 2
            
        return err
    
    def grad_w(self, x, y, w=None, b=None):
        w = self.w if w is None else w
        b = self.b if b is None else b
        
        y_pred = self.sigmoid(x)
        
        return (y_pred - y) * y_pred * (1 - y_pred) * x
    
    def grad_b(self, x, y, w=None, b=None):
        w = self.w if w is None else w
        b = self.b if b is None else b
        
        y_pred = self.sigmoid(x, w, b)
        
        return (y_pred - y) * y_pred * (1 - y_pred)
    
    def fit(self, X, Y, epochs=100, eta=0.01):
        self.w_h = []
        self.b_h = []
        self.e_h = []
        self.X = X
        self.Y = Y
        
        for i in range(epochs):
            dw, db = 0, 0
            for x, y in zip(X, Y):
                dw += self.grad_w(x, y)
                db += self.grad_b(x, y)
                
            self.w -= eta * dw / X.shape[0]
            self.b -= eta * db / X.shape[0]
            
            # log the error
            self.e_h.append(self.error(self.X, self.Y))
            self.w_h.append(self.w)
            self.b_h.append(self.b)


# In[105]:


X = np.asarray([3.5, 0.35, 3.2, -2.0, 1.5, -0.5])
Y = np.asarray([0.5, 0.50, 0.5, 0.5, 0.1, 0.3])


# In[106]:


w_init = -6
b_init = 4.0


# In[112]:


nn = NeuralNet(w_init=w_init, b_init=b_init)


# In[113]:


nn.fit(X, Y, epochs=4000, eta=1)


# In[114]:


plt.plot(nn.e_h, 'r')
plt.plot(nn.w_h, 'b')
plt.plot(nn.b_h, 'g')

plt.show()


# In[119]:


w_min = -7
w_max = 5

b_min = -7
b_max = 5

W = np.linspace(w_min, w_max, 256)
b = np.linspace(b_min, b_max, 256)
WW, BB = np.meshgrid(W, b)
Z = nn.error(X, Y, WW, BB)

fig = plt.figure(dpi=100)
ax = plt.subplot(111)
ax.set_xlabel('w')
ax.set_xlim(w_min - 1, w_max + 1)
ax.set_ylabel('b')
ax.set_ylim(b_min - 1, b_max + 1)
title = ax.set_title('Epoch 0')
cset = plt.contourf(WW, BB, Z, 25, alpha=0.6, cmap=cm.bwr)


# In[ ]:




