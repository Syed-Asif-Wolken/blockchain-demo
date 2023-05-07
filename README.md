# Blockchain Demo
Implementing a simple blockchain in java

Reference from [Baeldung](https://www.baeldung.com/java-blockchain)

### Requirements:

- java: 1.8 (amazon coretto)
- junit 5

### Block Params:
- Hash of the previous block, an important part to build the chain
- An actual data, any information having value, like a contract
- The timestamp of the creation of this block
- A nonce, which is an arbitrary number used in cryptography
- Finally, the hash of this block, calculated based on other data

### Hash Calculation
- First, we concatenate different parts of the block to generate a hash from
- Then, we get an instance of the SHA-256 hash function from MessageDigest
- Then, we generate the hash value of our input data, which is a byte array
- Finally, we transform the byte array into a hex string, a hash is typically represented as a 32-digit hex number

### Mining the Block
- We start by defining the prefix we desire to find
- Then we check whether we've found the solution
- If not we increment the nonce and calculate the hash in a loop
- The loop goes on until we hit the jackpot

**Note:** Nonce starts from 0 and is incremented by 1 here. More sophisticated strategies to start and increment a nonce are available in real-world applications.

### Block Validation
- The stored hash of the current block is actually what it calculates
- The hash of the previous block stored in the current block is the hash of the previous block
- The current block has been mined






