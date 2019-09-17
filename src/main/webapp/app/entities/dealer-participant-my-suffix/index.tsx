import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import DealerParticipantMySuffix from './dealer-participant-my-suffix';
import DealerParticipantMySuffixDetail from './dealer-participant-my-suffix-detail';
import DealerParticipantMySuffixUpdate from './dealer-participant-my-suffix-update';
import DealerParticipantMySuffixDeleteDialog from './dealer-participant-my-suffix-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={DealerParticipantMySuffixUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={DealerParticipantMySuffixUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={DealerParticipantMySuffixDetail} />
      <ErrorBoundaryRoute path={match.url} component={DealerParticipantMySuffix} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={DealerParticipantMySuffixDeleteDialog} />
  </>
);

export default Routes;
