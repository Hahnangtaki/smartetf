import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './dealer-participant-my-suffix.reducer';
import { IDealerParticipantMySuffix } from 'app/shared/model/dealer-participant-my-suffix.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IDealerParticipantMySuffixProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export class DealerParticipantMySuffix extends React.Component<IDealerParticipantMySuffixProps> {
  componentDidMount() {
    this.props.getEntities();
  }

  render() {
    const { dealerParticipantList, match } = this.props;
    return (
      <div>
        <h2 id="dealer-participant-my-suffix-heading">
          Dealer Participants
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />
            &nbsp; Create a new Dealer Participant
          </Link>
        </h2>
        <div className="table-responsive">
          {dealerParticipantList && dealerParticipantList.length > 0 ? (
            <Table responsive>
              <thead>
                <tr>
                  <th>ID</th>
                  <th>Dealer Id</th>
                  <th>Dealer Code</th>
                  <th>Dealer Name</th>
                  <th />
                </tr>
              </thead>
              <tbody>
                {dealerParticipantList.map((dealerParticipant, i) => (
                  <tr key={`entity-${i}`}>
                    <td>
                      <Button tag={Link} to={`${match.url}/${dealerParticipant.id}`} color="link" size="sm">
                        {dealerParticipant.id}
                      </Button>
                    </td>
                    <td>{dealerParticipant.dealerId}</td>
                    <td>{dealerParticipant.dealerCode}</td>
                    <td>{dealerParticipant.dealerName}</td>
                    <td className="text-right">
                      <div className="btn-group flex-btn-group-container">
                        <Button tag={Link} to={`${match.url}/${dealerParticipant.id}`} color="info" size="sm">
                          <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                        </Button>
                        <Button tag={Link} to={`${match.url}/${dealerParticipant.id}/edit`} color="primary" size="sm">
                          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                        </Button>
                        <Button tag={Link} to={`${match.url}/${dealerParticipant.id}/delete`} color="danger" size="sm">
                          <FontAwesomeIcon icon="trash" /> <span className="d-none d-md-inline">Delete</span>
                        </Button>
                      </div>
                    </td>
                  </tr>
                ))}
              </tbody>
            </Table>
          ) : (
            <div className="alert alert-warning">No Dealer Participants found</div>
          )}
        </div>
      </div>
    );
  }
}

const mapStateToProps = ({ dealerParticipant }: IRootState) => ({
  dealerParticipantList: dealerParticipant.entities
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(DealerParticipantMySuffix);
