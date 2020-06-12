#!/usr/bin/env bash

main () {
	(($# > 0)) || err_exit 'Usage: ko.sh start# count'
	pn=$1
	cnt=${2:-1}
	for (( x = 0; x < $cnt; x++ )); do
		leetcode show -gx $pn
		open $pn.*
		(( pn++))
	done
}

err_exit(){
	echo "$1"
	exit 1 
}

main "$@"