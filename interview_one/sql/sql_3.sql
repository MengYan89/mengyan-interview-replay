SELECT
	t1.S_NO
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
	) t1
WHERE
	t1.C_NO = 111
	AND EXISTS (
          SELECT
	        1
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
	      ) t2
          WHERE
	        t2.C_NO = 112
	        AND t1.S_NO = t2.S_NO
	        AND t1.SCORE > t2.SCORE
	);