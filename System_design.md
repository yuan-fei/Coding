# Notes

* SNAKE
	* Scenario: case and interface
	* Neccesary: constraint and hypothesis
	* Application: service and algorithm
	* Kilobit: data storage design
	* Evolve: performance, scalability and robustness
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
	* cache aside: use both cache and DB explicitly
	* write through: cache as data store, cache is responsible for R/w DB
		* cache return data after persisted
	* write behind: 
		* Add/update entry in cache
		* Asynchronously write entry to the data store, improving write performance
	* refresh ahead
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
	* base62: [a-zA-Z0-9]
* Twitter timeline implementation
	* Timeline
		* User timeline: self tweets
		* Home timeline: aggregated following tweets
	* Fanout with redis: compute and store home timeline for each new tweet
		* hot users (lots of followers): pull + merge with precomputed home timeline
	* Reference
		* [system-design-primer](https://github.com/donnemartin/system-design-primer/blob/master/solutions/system_design/twitter/README.md)
		* [highscalability.com](http://highscalability.com/blog/2013/7/8/the-architecture-twitter-uses-to-deal-with-150m-active-users.html)
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