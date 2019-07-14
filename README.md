# Tag Force Medal Predictor

A program to predict the medal distribution in Yu-Gi-Oh! GX Tag Force.

## Install

Install Java from Oracle's website, version 10 or above. Then grab the jar from
the [Releases page](../../releases). The jar there is for Windows; I think with
Java 10 but not 11 it will work with other OSes but I've not tested this.

## Usage

I assume you know what the medal farm in Tag Force is. You enter on the left the
medals some duelists give, and then by pressing the 'Predict Medals' button on
the right the program can predict the medals the remaining duelists give. The
'Reset Known Medals' button clears the currently entered medals.

By clicking Options you can select whether you're running on PS2 or PSP, who
your partner is, whether to filter out low medal counts (this only shows pairs
giving 5, unless that doesn't add up to 80 medals in which case it also shows
pairs giving 4, and so on), and some settings to do with PSP.

Under the predicted medals on the right you can see the number of seeds the
predictor is able to narrow down to, giving you a rough idea of how much more
information is needed. A rule of thumb is that every duelist cuts out 80% of
seeds, leaving you with 20% or a fifth. This is only approximate though. The
number of 5s tells you the number of pairs giving you 5 medals. If this is under
16, you need to duel a 4 or below to get the 80 needed medals.

Now PSP is special. There are a lot more possible seeds, but this can be
narrowed down with knowledge of the PSP's time. By clicking 'Record Current
Time' the program stores the current time of your computer's clock, and with
'Clear Current Time' you can clear this. You want to record the current time
immediately after confirming your choice of part 2 partner.

If you are on emu, make sure that PPSSPP is synced with your computer clock. To
do this, on the emu's main screen go to Settings > System and tick 'Force real
clock sync (slower, less lag)'. If you are on console, make sure that your PSP's
system time is accurate.

In experiments with PPSSPP I found the computer time is off a bit from the time
the game takes from the emu, specifically the emu time was about 13 seconds
behind. You can adjust for this by changing 'PSP Timer Delay' in the options,
which is a whole number of seconds. This is how many seconds behind the emu time
is from the computer time. To help with this there's a 'Time off' result under
the prediction; it tells you how far ahead or behind the seed was (taking into
account the delay). So you add the time off value to the delay until you're
happy with the result. The 'PSP Timer Uncertainty' is how far off the recorded
computer time to search. For comparison's sake, a figure of 8 seconds here is
about equivalent to the search space for PS2.