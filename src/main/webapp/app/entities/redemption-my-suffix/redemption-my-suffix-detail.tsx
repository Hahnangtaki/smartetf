import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './redemption-my-suffix.reducer';
import { IRedemptionMySuffix } from 'app/shared/model/redemption-my-suffix.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IRedemptionMySuffixDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class RedemptionMySuffixDetail extends React.Component<IRedemptionMySuffixDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { redemptionEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            Redemption [<b>{redemptionEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="redemptionId">Redemption Id</span>
            </dt>
            <dd>{redemptionEntity.redemptionId}</dd>
            <dt>
              <span id="redemptionCode">Redemption Code</span>
            </dt>
            <dd>{redemptionEntity.redemptionCode}</dd>
            <dt>
              <span id="redemptionDate">Redemption Date</span>
            </dt>
            <dd>
              <TextFormat value={redemptionEntity.redemptionDate} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="customerId">Customer Id</span>
            </dt>
            <dd>{redemptionEntity.customerId}</dd>
            <dt>
              <span id="customerName">Customer Name</span>
            </dt>
            <dd>{redemptionEntity.customerName}</dd>
            <dt>
              <span id="unitSellPriceInd">Unit Sell Price Ind</span>
            </dt>
            <dd>{redemptionEntity.unitSellPriceInd}</dd>
            <dt>
              <span id="unitSellPrice">Unit Sell Price</span>
            </dt>
            <dd>{redemptionEntity.unitSellPrice}</dd>
            <dt>
              <span id="unitSellUnit">Unit Sell Unit</span>
            </dt>
            <dd>{redemptionEntity.unitSellUnit}</dd>
            <dt>
              <span id="unitSellLot">Unit Sell Lot</span>
            </dt>
            <dd>{redemptionEntity.unitSellLot}</dd>
            <dt>
              <span id="unitSellBasket">Unit Sell Basket</span>
            </dt>
            <dd>{redemptionEntity.unitSellBasket}</dd>
            <dt>
              <span id="market">Market</span>
            </dt>
            <dd>{redemptionEntity.market}</dd>
            <dt>
              <span id="sellGrossAmount">Sell Gross Amount</span>
            </dt>
            <dd>{redemptionEntity.sellGrossAmount}</dd>
            <dt>
              <span id="fee">Fee</span>
            </dt>
            <dd>{redemptionEntity.fee}</dd>
            <dt>
              <span id="sellNetAmount">Sell Net Amount</span>
            </dt>
            <dd>{redemptionEntity.sellNetAmount}</dd>
            <dt>
              <span id="statusCash">Status Cash</span>
            </dt>
            <dd>{redemptionEntity.statusCash}</dd>
            <dt>
              <span id="statusEtf">Status Etf</span>
            </dt>
            <dd>{redemptionEntity.statusEtf}</dd>
            <dt>
              <span id="status">Status</span>
            </dt>
            <dd>{redemptionEntity.status}</dd>
            <dt>
              <span id="channel">Channel</span>
            </dt>
            <dd>{redemptionEntity.channel}</dd>
            <dt>
              <span id="dealerId">Dealer Id</span>
            </dt>
            <dd>{redemptionEntity.dealerId}</dd>
            <dt>
              <span id="productId">Product Id</span>
            </dt>
            <dd>{redemptionEntity.productId}</dd>
            <dt>
              <span id="underlyingId">Underlying Id</span>
            </dt>
            <dd>{redemptionEntity.underlyingId}</dd>
          </dl>
          <Button tag={Link} to="/entity/redemption-my-suffix" replace color="info">
            <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/entity/redemption-my-suffix/${redemptionEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ redemption }: IRootState) => ({
  redemptionEntity: redemption.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(RedemptionMySuffixDetail);
