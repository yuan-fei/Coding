# Notes

* SNAKE
	* Scenario: case and interface
	* Neccesary: constraint and hypothesis
	* Application: service and algorithm
	* Kilobit: data storage design
	* Evolve: performance, scalability and robustness
* Back of the envelope estimation
	* 1 day = 86000s
	* disk seek: 10ms
	* 1ms disk sequential read: 20ms
* CAP
* DNS
	* Category
		* Recursive DNS = Resolver: delegate user DNS request
		* Authorative DNS: 
			* Root DNS: redirect request to TLD
			* TLD(Top level domain): .com, .cn, ... 
			* Route53: example.com
	* reference: [How Does DNS Route Traffic To Your Web Application?](https://aws.amazon.com/route53/what-is-dns/)
* CDN
	* advantages
		* Users receive content at data centers close to them
		* Your servers do not have to serve requests that the CDN fulfills
* Load balance: 
	* distributes incoming client requests among a group of servers, in each case returning the response from the selected server to the appropriate client
	* Architecture
		* Master/LB: do IO
		* Slave: computation resource
	* Layer 4 Load balancing: transport layer
		* source ip, destination ip, port
	* Layer 7 Load balancing: application layer
		* all info: source ip, destination ip, port, content...
* Reverse Proxy
	* accepts a request from a client, forwards it to a server that can fulfill it, and returns the server’s response to the client.
	* Increased security, Increased scalability and flexibility
	* reference: [reverse-proxy-vs-load-balancer](https://www.nginx.com/resources/glossary/reverse-proxy-vs-load-balancer/)
* Cache
	* Type
		* cache aside pattern: use both cache and DB explicitly
			* read: when cache miss, load data from db and update cache 
			* write: write to db first and invalidate cache
		* write through: cache as data store, cache is responsible for R/w DB
			* cache return data after persisted
		* write behind: 
			* Add/update entry in cache
			* Asynchronously write entry to the data store, improving write performance
		* refresh ahead
	* How to update cache correctly?
		* factors
			* concurrency: multiple r/w
			* failure: cache operation failure, db operation failure
		* solutions
			* cache invalidate instead of cache set: old data can be loaded to cache during concurrency (multiple writes)
			* 2 incomplete solutions
				* invalidate cache + update db
					* pros: consistent over failure (cache failure or db faliure)
					* cons: inconsistent over concurrency (i1, r2, w1)
				* update db + invalidate cache (cache aside pattern)
					* pros: consistent over concurrency (different order of r/w operations)
					* cons: inconsistent over failure (cache invalidation failure)
		* Reference: 
			* [缓存更新的套路](https://coolshell.cn/articles/17416.html)
			* [缓存与数据库的双写一致性](https://github.com/doocs/advanced-java/blob/master/docs/high-concurrency/redis-consistence.md)
			* [缓存雪崩、穿透和击穿](https://github.com/doocs/advanced-java/blob/master/docs/high-concurrency/redis-caching-avalanche-and-caching-penetration.md)
* Database
	* index
		* implementation: 
			* BTree for range: log<sub>b</sub>n
			* B+Tree: disk friendly with continuous reading
		* Clustered vs. non-clustered
			* clustered index sort rows in key order phisically
			* [How does insert a row work with clustered index](https://stackoverflow.com/questions/11601345/insert-into-table-with-clustered-index): store rows in pages, and insert modifies/split only 1 page
	* log for recovery
		* [redo log](http://www.mathcs.emory.edu/~cheung/Courses/554/Syllabus/6-logging/redo1.html): redo commited transactions
			1. log: changed/modified values in a transaction
			2. log: commit
			3. flush data to disk (async operaton for performance)
* Consistent Hashing for Sharding
	* Problem to solve:
	    * Elastic scaling: dynamic add/remove nodes
	    * Remap keys when adjust nodes: as few as possible
	    * Avoid hot spot/imbalance on nodes
	* Solution
	    * Design
	        1. Circular Hash key space: int32
	        2. Map node to key space
	        3. How to place key? Find the upper bound/lower bound of hashed key
	    * When add/remove node
	        * Only k/n keys needs to be remapped
	    * Use multiple virtual node/replica of a node to balance keys
	* Reference:
	https://www.acodersjourney.com/system-design-interview-consistent-hashing/

* Squid, ATS, Varnish, HAProxy, Nginx
	* Squid, ATS, Varnish: caching / CDN
	* HAProxy, Nginx: reverse proxy
	* Nginx: web server
* Pastebin/bitly
	* Services: shortening + redirect
	* read:write = 10:1
	* shortening
		* short_url = base62(increment counter++)
		* base62: [a-zA-Z0-9]
* Twitter timeline implementation
	* Timeline
		* User timeline: self tweets
		* Home timeline: aggregated following tweets
	* Push
		* Fanout with redis: compute and store home timeline for each new tweet
	* Pull
		* Compute based on tweet list
	* Mixed
		* hot users (lots of followers): pull + merge with precomputed home 
	* Reference
		* [system-design-primer](https://github.com/donnemartin/system-design-primer/blob/master/solutions/system_design/twitter/README.md)
		* [highscalability.com](http://highscalability.com/blog/2013/7/8/the-architecture-twitter-uses-to-deal-with-150m-active-users.html)
		* ninechapter 6
* KV store with external storage/ Facebook photo storage
	* index (in mem + file) + data file
		* index: key -> data offset in data file
	* CRUD
		* C(K, V): append V to data file + add index K -> offset of V in data file
		* R(K): offset = index(K) + get V at index
		* U(K, V): append V to data file + update index K -> offset of new V in data file
		* D(K): delete K in index
	* Compact/GC
		* copy all indexed value to new file: O(n)
* User system
	* Scenario
		* account: register/update profile/remove
		* session: login/logout
		* wallet: balance/membership
	* User State management: 
		* User (uid, name, hashed_pw, state)
			* register, approve, disapprove, deactivate, ban
	* User session management: 
		* Session (uid, session_id, state)
			* login, logout
	* Membership management
		* membership (uid, balance, end_time)
			* charge, extend\_end\_time
		* transaction for consistency
	* Availability
		* translog for failure recover
		* Redundency against data loss
* Payment system
	* Scenario
		* charge(deposit)/withdraw
		* transfer/payment
	* Neccessary
		* QPS of charge
	* Application (charge)
		* Order: bank account -> app account
	* Data
		* User: userId
		* Order: orderId, bankId, userId, amount, opType, state, time
		* Payment Method: bankId, userId
* Crawler system
	* crawl list and page
	* Master-slave
		* scheduler: control each crawler machine 
			* task table: 
				* taskId, priority, type (list/detail), url, time 
			* page table:
				* page content
		* slave: worker
	* pull: 
		* slaves monitor task queue/database
		* take a task and crawl
		* update task status
		* concurrency: producer-consumer pattern
	* push
		* master pushes task to slave
		* slave sends crawled page to master
* Search Engine
	* Core components
		* Crawler for data retrieve
		* Indexing service
			* reverse index service + document service
		* Search API
* Tiny Url
	* Scenario
		* long -> short
		* short -> long
	* Neccesary
		* 1M dau: 
		* QPS 
			* L->S: 1M * 1% * 10func / 86000 = 1.2
			* S->L: 1M * 100% * 3func / 86000 = 35
	* Application
		* L->S: return table/list size as shorturl
			* long number growing: log10(total)
				* use base62 encoding: log62(total)
	* Kilo
		* 100K urls / day * 100B(L) + 8B(S) + 4B(state) = 10MB/day
	* Extensibility
		* How to support random?
			* random(0, range), avoid conflicting by try again
		* How to support expire?
			* expire/state
		* How to support analysis
			* log access event + time bucket
* Distributed file system
	* Large file: metadata + chunks
		* metadata: index (chuck_id -> offset)
		* chucks: 64M per chunk
		* +: reduce metadata size, reduce traffic
		* -: waste space for small files
	* Extra large file: chunk server
		* master metadata: index (chunck_id -> chunck server id)
		* chunck server: 
			* metadata: index (chunck_id -> offset)
			* chuncks
	* Chunck robustness:
		* checksum: check chuck is broken
		* replica: chunck copies on multiple server
			* performance + availability: 2 on same rack + 1 on remote rack
	* Chunck server aliveness
		* keepalive with master
	* Chunck server recover
		* Once chuck server down, it's 'dead', never go back online
		* master replicate chuncks to other chunck server
	* Hot spot: hot file
		* Master rebalance to replicate chuncks to more chunck server
	* Read a file:
		1. client query chunck server id of block from name node
		2. client read from chunck server
	* Write a file ([pipeline replication](https://hadoop.apache.org/docs/r1.2.1/hdfs_design.html#Replication+Pipelining))
	 	1. client query chunck server id list (primary + replicas) of block from name node
	 	2. client send data to primary
	 	3. chunck server chain: primary->replica1->replica2
		 	* 2-phase commit persist
	 	4. primary chunck server ack client
* Big table
	* phisical view
		* big table = a list of tablets
			* tablet = a list of sorted <key, values> pairs
				* a list of sstable
		* SStable (Sorted String Table): [sstable-and-log-structured-storage-leveldb/](https://www.igvita.com/2012/02/06/sstable-and-log-structured-storage-leveldb/)
		* mem+file
			* memory: 
				* index(sstable -> file address, bloomfilter)
				* sstable(sorted by key)
			* file: sstables
		* how to read: find in all sstables and merge the result
		* how to write: 
			* write to sstable in memory 
			* write operation transaction log to disk
			* dump the sstable to file system when full
		* How to store data on GFS
			* a server can be both tablet server (memory) + chunck server (persistent)
			* log, sstable replicates on chunck server
	* Logical view
		* row key: (column:col_value)* -> timestamp
	* Architecture (see evernote 'hbase')
		* client
		* chubby: metadata for tablet server addresses
		* master: manage metadata, balance load, health check
		* client read/write data from tablet server
* Map-reduce
	* process
		* input-split-map-shuffle-reduce-finalize
		* split size = block size = 64M
* Big data problem solution
	* segment hash
	* bitmap
	* bloomfilter
	* external sort
	* map-reduce
* Rate limiter
	* limit qps to k
		* time bucket (tumbling window)
			* 1 bucket per second, limit k request for 1 bucket
			
			~~~
			// single time bucket implementation
			long cur_ts = 0;
			int remaining = k;
			boolean allow(long ts){
				if(ts != cur_ts){
					remaining = k;
					cur_ts = ts;
				}
				if(remaining <= 0){
					return false;
				}
				else{
					remaining--;
					return true;
				}
			}
			~~~
		* sliding window
			* record enqueue time of each request
			* for each request q<sub>n</sub>, it can be added to queue only when
				* queue has less than k items, or
				* now - t<sub>n-k</sub> > 1s
* Seconds kill: 10 iphones
	* Cache: browser, CDN, reverse proxy, server cache
	* Queue: 2 level queue
		* level 1 (de-peak traffic): n web servers, each has a queue, each allows 10 requests
		* level 2: 1 queue for first 10 of 10*n requests
	* Key point
		* reduce traffic
			* recude page size
			* CDN for static page
			* limit request
* Typeahead
	* Scenario
		* return all suggestions(articles, friends, ...) according to the typed words
	* Neccessary
		* average latency < 100ms
	* Application
		* trie-tree
	* Architecture
		* aggregation of inverted index (global/personalized)
		* cache result

* Bloomfilter vs. hashset: 
	* +: space saving
	* -: hard handle element delete (we can leave it and false positive rate will increase)
* QA
	* SQL or Nosql ([sql or nosql](https://github.com/donnemartin/system-design-primer#sql-or-nosql))
		* Difference? 
			* relational(tabular model with schema) vs. non-relational(schemaless)
				* structural data vs. semi-structural data 
					* KV, doc, graph
			* SQL vs. proprietary query languange
			* strong consistency vs. eventual consistency
		* scenario for nosql
			* Temporal data: log data
	* Java GC: [Oracle](https://www.oracle.com/webfolder/technetwork/tutorials/obe/java/gc01/index.html)
		* Mark and compact, Mark and copy
		* generational
			* young, old, permernant
			* young: 
				* eden: where objects allocation happens
				* s0, s1: survivor space
			* minor GC on young
				* copy live objects of eden and si to s(1-i), sweep eden and si space
				* copy old (i.e. >survived over 8 minor GCs) live objects to old gen
			* major GC on old
* Reference:
	* [System design primer](https://github.com/donnemartin/system-design-primer)
	* [Scaling Up to Your First 10 Million Users](https://www.youtube.com/watch?v=w95murBkYmU)
	* [Latency Numbers Every Programmer Should Know](https://gist.github.com/jboner/2841832)