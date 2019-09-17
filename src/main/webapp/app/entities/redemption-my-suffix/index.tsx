import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import RedemptionMySuffix from './redemption-my-suffix';
import RedemptionMySuffixDetail from './redemption-my-suffix-detail';
import RedemptionMySuffixUpdate from './redemption-my-suffix-update';
import RedemptionMySuffixDeleteDialog from './redemption-my-suffix-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={RedemptionMySuffixUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={RedemptionMySuffixUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={RedemptionMySuffixDetail} />
      <ErrorBoundaryRoute path={match.url} component={RedemptionMySuffix} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={RedemptionMySuffixDeleteDialog} />
  </>
);

export default Routes;
