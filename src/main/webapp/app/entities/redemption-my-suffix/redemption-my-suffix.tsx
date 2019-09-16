import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { ICrudGetAllAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './redemption-my-suffix.reducer';
import { IRedemptionMySuffix } from 'app/shared/model/redemption-my-suffix.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IRedemptionMySuffixProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export class RedemptionMySuffix extends React.Component<IRedemptionMySuffixProps> {
  componentDidMount() {
    this.props.getEntities();
  }

  render() {
    const { redemptionList, match } = this.props;
    return (
      <div>
        <h2 id="redemption-my-suffix-heading">
          Redemptions
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />
            &nbsp; Create a new Redemption
          </Link>
        </h2>
        <div className="table-responsive">
          {redemptionList && redemptionList.length > 0 ? (
            <Table responsive aria-describedby="redemption-my-suffix-heading">
              <thead>
                <tr>
                  <th>ID</th>
                  <th>Redemption Code</th>
                  <th>Redemption Date</th>
                  <th>Customer Id</th>
                  <th>Customer Name</th>
                  <th>Unit Sell Price Ind</th>
                  <th>Unit Sell Price</th>
                  <th>Unit Sell Unit</th>
                  <th>Unit Sell Lot</th>
                  <th>Unit Sell Basket</th>
                  <th>Market</th>
                  <th>Sell Gross Amount</th>
                  <th>Fee</th>
                  <th>Sell Net Amount</th>
                  <th>Status Cash</th>
                  <th>Status Etf</th>
                  <th>Status</th>
                  <th>Channel</th>
                  <th>Etf Product</th>
                  <th>Dealer Participant</th>
                  <th>Etf Execution Dtl</th>
                  <th />
                </tr>
              </thead>
              <tbody>
                {redemptionList.map((redemption, i) => (
                  <tr key={`entity-${i}`}>
                    <td>
                      <Button tag={Link} to={`${match.url}/${redemption.id}`} color="link" size="sm">
                        {redemption.id}
                      </Button>
                    </td>
                    <td>{redemption.redemptionCode}</td>
                    <td>
                      <TextFormat type="date" value={redemption.redemptionDate} format={APP_LOCAL_DATE_FORMAT} />
                    </td>
                    <td>{redemption.customerId}</td>
                    <td>{redemption.customerName}</td>
                    <td>{redemption.unitSellPriceInd}</td>
                    <td>{redemption.unitSellPrice}</td>
                    <td>{redemption.unitSellUnit}</td>
                    <td>{redemption.unitSellLot}</td>
                    <td>{redemption.unitSellBasket}</td>
                    <td>{redemption.market}</td>
                    <td>{redemption.sellGrossAmount}</td>
                    <td>{redemption.fee}</td>
                    <td>{redemption.sellNetAmount}</td>
                    <td>{redemption.statusCash}</td>
                    <td>{redemption.statusEtf}</td>
                    <td>{redemption.status}</td>
                    <td>{redemption.channel}</td>
                    <td>
                      {redemption.etfProductId ? (
                        <Link to={`etf-product-my-suffix/${redemption.etfProductId}`}>{redemption.etfProductId}</Link>
                      ) : (
                        ''
                      )}
                    </td>
                    <td>
                      {redemption.dealerParticipantId ? (
                        <Link to={`dealer-participant-my-suffix/${redemption.dealerParticipantId}`}>{redemption.dealerParticipantId}</Link>
                      ) : (
                        ''
                      )}
                    </td>
                    <td>
                      {redemption.etfExecutionDtlId ? (
                        <Link to={`etf-execution-dtl-my-suffix/${redemption.etfExecutionDtlId}`}>{redemption.etfExecutionDtlId}</Link>
                      ) : (
                        ''
                      )}
                    </td>
                    <td className="text-right">
                      <div className="btn-group flex-btn-group-container">
                        <Button tag={Link} to={`${match.url}/${redemption.id}`} color="info" size="sm">
                          <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                        </Button>
                        <Button tag={Link} to={`${match.url}/${redemption.id}/edit`} color="primary" size="sm">
                          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                        </Button>
                        <Button tag={Link} to={`${match.url}/${redemption.id}/delete`} color="danger" size="sm">
                          <FontAwesomeIcon icon="trash" /> <span className="d-none d-md-inline">Delete</span>
                        </Button>
                      </div>
                    </td>
                  </tr>
                ))}
              </tbody>
            </Table>
          ) : (
            <div className="alert alert-warning">No Redemptions found</div>
          )}
        </div>
      </div>
    );
  }
}

const mapStateToProps = ({ redemption }: IRootState) => ({
  redemptionList: redemption.entities
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(RedemptionMySuffix);
