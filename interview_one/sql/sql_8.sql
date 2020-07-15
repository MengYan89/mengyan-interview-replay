SELECT
	t.C_NO,
	MIN( t.SCORE ) AS MIN_SCORE,
	MAX( t.SCORE ) AS MAX_SCORE
FROM
	(
    SELECT
	  s.S_NO,
	  c.C_NO,
	  IFNULL( sc.SCORE, 0 ) AS SCORE
    FROM
	  ( STUDENT s, COURSE c )
	LEFT JOIN SC sc ON s.S_NO = sc.S_NO
	                  AND sc.C_NO = c.C_NO
    GROUP BY
	  s.S_NO,
	  c.C_NO
	) t
GROUP BY
	t.C_NO;