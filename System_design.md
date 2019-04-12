# Notes

* SNAKE
	* Scenario: case and interface
	* Neccesary: constraint and hypothesis
	* Application: service and algorithm
	* Kilobit: data storage design
	* Evolve: performance, scalability and robustness
* CAP
* CDN
	* advantages
		* Users receive content at data centers close to them
		* Your servers do not have to serve requests that the CDN fulfills
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