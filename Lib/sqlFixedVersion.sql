-- select * from TopTrumps.gameStats;

-- insert into topTrumps.gamestats
-- values (default, true, 37,23,20,16,4,13,123);

-- delete from topTrumps.gameStats
-- where gameid=2;

select 
	count(gameid) as gameCount, 
	count(*) filter(where isHumanWon) as humanWon, 
	count(*) filter(where not isHumanWon) as AIWon,
	round(avg(draws)::numeric,1) as averageDraws, 
	max(roundNumber) as largestRound
from TopTrumps.gameStats;



-- select count(isHumanWon) as humanWon
-- from TopTrumps.gameStats
-- where isHumanWon=false;

-- select avg(draws) as averageDraws
-- from TopTrumps.gameStats;

-- select max(roundNumber) as largestRound
-- from TopTrumps.gameStats;