# peaceland_pj

## Preliminary questions :

1) What technical/business constraints should the data storage component of theprogram architecture meet to fulfill the requirement described by the customer inparagraph «Statistics»?So what kind of component(s) (listed in the lecture) will the architecture need?

To fulfill the requirement described by the customer in paragraph «Statistics», the data storage component of the program architecture should be able to accept a large amount of data simultaneously, as well as be able to write quickly.


2) What business constraint should the architecture meet to fulfill the requirementdescribe in the paragraph «Alert»? Which component to choose?

To fulfill the requirement describe in the paragraph « alert » we must have the P (Partition Tolerance) of the CAP theorem. The best thing would be to choose the consistency instead of the availability because we need to have the right information but the subject said « the peacemaker reaction must be as fast as possible » So we take the A for availability. So, we choose AP for availability and partition tolerance.
Because we need to send lot of small information like location and name, we must use stream component like why not kafka.

3) What mistake(s) from Peaceland can explain the failed attempt?

The misstep is the way that Peaceland employ an data scientist team which are not able to this kind of assignment. They are qualified to construct AI models and make forecasts, but here the inquiry is to plan the design and make versatile projects.

4) Peaceland has likely forgotten some technical information in the report sent by thedrone. In the future, this information could help Peaceland make its peacewatchersmuch more efficient. Which information?

  They seem to have forgotten to include the date and time in their report. This could be interesting to establish statistics, frequencies... This could be used to determine if it is possible to determine the place and time of an incident for example.
More fancifully, it could be interesting to collect the general sound volume in each report as well as the presence, or not, of sudden movements. This data could be used as a training base to predict, and quickly defuse, a situation that is escalating.
