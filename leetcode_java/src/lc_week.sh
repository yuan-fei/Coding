#!/usr/bin/env bash

main () {
	(($# == 1)) || err_exit 'Usage: lc_week.sh start#'
	pn=$1
	for (( x = 0; x < 4; x++ )); do
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