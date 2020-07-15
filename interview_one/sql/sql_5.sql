SELECT
	s.S_NO,
	( SUM( s.SCORE ) / ( SELECT COUNT( 1 ) FROM COURSE ) ) AS AVG
FROM
	SC s
GROUP BY
	s.S_NO
HAVING
	( SUM( s.SCORE ) / ( SELECT COUNT( 1 ) FROM COURSE ) ) > 60;